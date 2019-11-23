package ch.ethz.geco.t4j.internal;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;
import reactor.util.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Requests {
    private final GECoClient gecoClient;
    private final HttpClient httpClient;

    public enum METHOD {GET, POST, DELETE, PATCH}

    /**
     * Constructs a new Request holder.
     *
     * @param client The GECo client, can be null if no API key is needed.
     */
    public Requests(GECoClient client) {
        gecoClient = client;
        httpClient = HttpClient.create().headers(h -> {
            h.add("X-API-KEY", client.getAPIToken());
            h.add("Content-Type", "application/json");
        }).baseUrl(Endpoints.BASE);
    }

    /**
     * Gets the shared http client.
     *
     * @return the HTTP client.
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    private byte[] readAllBytes(InputStream inputStream) throws IOException {
        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        int capacity = buf.length;
        int nread = 0;
        int n;
        for (; ; ) {
            // read to EOF which may read more or less than initial buffer size
            while ((n = inputStream.read(buf, nread, capacity - nread)) > 0)
                nread += n;

            // if the last call to read returned -1, then we're done
            if (n < 0)
                break;

            // need to allocate a larger buffer
            if (capacity <= MAX_BUFFER_SIZE - capacity) {
                capacity = capacity << 1;
            } else {
                if (capacity == MAX_BUFFER_SIZE)
                    throw new OutOfMemoryError("Required array size too large");
                capacity = MAX_BUFFER_SIZE;
            }
            buf = Arrays.copyOf(buf, capacity);
        }
        return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
    }

    private <T> Mono<T> makeAndroidRequest(METHOD method, String url, Class<T> clazz, String content) {
        return Mono.fromSupplier(() -> 0).flatMap(a -> {
            try {
                HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

                request.setRequestProperty("X-API-KEY", gecoClient.getAPIToken());
                request.setRequestProperty("Content-Type", "application/json");

                request.setRequestMethod(method.name());
                if (method != METHOD.GET) {
                    request.setDoOutput(true);

                    OutputStream outputStream = request.getOutputStream();
                    outputStream.write(content.getBytes(StandardCharsets.UTF_8));
                    outputStream.close();
                }

                request.connect();
                int responseCode = request.getResponseCode();

                InputStream inputStream = request.getInputStream();
                byte[] bytes = readAllBytes(inputStream);

                String data = new String(bytes, StandardCharsets.UTF_8);
                if (responseCode == 403 || responseCode == 404 || responseCode == 424) {
                    JsonNode jsonNode = GECoUtils.MAPPER.readTree(data);

                    JsonNode message = jsonNode.get("message");
                    JsonNode code = jsonNode.get("code");

                    if (code == null) {
                        return Mono.error(new GECo4JException("Error on request to " + request.getURL() + ". Received response code " + responseCode + ". With response text: " + data));
                    }

                    return Mono.error(new APIException(message != null ? message.asText() : "None", code.asInt()));
                } else if (responseCode < 200 || responseCode > 299) {
                    return Mono.error(new GECo4JException("Error on request to " + request.getURL() + ". Received response code " + responseCode + ". With response text: " + data));
                }

                return Mono.just(GECoUtils.MAPPER.readValue(data, clazz));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return Mono.empty();
        });
    }

    public <T> Mono<T> makeRequest(METHOD method, String url, Class<T> clazz, @Nullable String content) {
        // Use java.net for Android since netty seems to be problematic
        if (!System.getProperty("java.vm.vendor", "").equals("The Android Project")) {
            return makeAndroidRequest(method, Endpoints.BASE + url, clazz, content).subscribeOn(Schedulers.single());
        }

        HttpClient.ResponseReceiver<?> receiver = null;
        switch (method) {
            case GET:
                receiver = httpClient.get().uri(url);
                break;
            case POST:
                receiver = httpClient.post().uri(url).send(ByteBufFlux.fromString(content != null ? Flux.just(content) : Flux.empty()));
                break;
            case PATCH:
                receiver = httpClient.patch().uri(url).send(ByteBufFlux.fromString(content != null ? Flux.just(content) : Flux.empty()));
                break;
            case DELETE:
                receiver = httpClient.delete().uri(url).send(ByteBufFlux.fromString(content != null ? Flux.just(content) : Flux.empty()));
                break;
        }

        return receiver.responseSingle((response, responseContent) -> {
            int responseCode = response.status().code();
            return responseContent.asString().flatMap(data -> {
                if (responseCode == 403 || responseCode == 404 || responseCode == 424) {
                    JsonNode jsonNode;
                    try {
                        jsonNode = GECoUtils.MAPPER.readTree(data);

                        JsonNode message = jsonNode.get("message");
                        JsonNode code = jsonNode.get("code");

                        // If it's not a "controlled" exception
                        if (code == null) {
                            return Mono.error(new GECo4JException("Error on request to " + response.uri() + ". Received response code " + responseCode + ". With response text: " + data));
                        }

                        return Mono.error(new APIException(message != null ? message.asText() : "None", code.asInt()));
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
                } else if (responseCode < 200 || responseCode > 299) {
                    return Mono.error(new GECo4JException("Error on request to " + response.uri() + ". Received response code " + responseCode + ". With response text: " + data));
                }

                try {
                    if (clazz == null) {
                        return Mono.empty();
                    }

                    return Mono.just(GECoUtils.MAPPER.readValue(data, clazz));
                } catch (IOException e) {
                    return Mono.error(e);
                }
            });
        });
    }
}
