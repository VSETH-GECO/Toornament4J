package ch.ethz.geco.t4j.internal.auth;

import ch.ethz.geco.t4j.internal.Endpoints;
import ch.ethz.geco.t4j.internal.Requests;
import ch.ethz.geco.t4j.internal.ToornamentUtils;
import ch.ethz.geco.t4j.internal.json.objects.TokenObject;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

public class Authorization {
    private static final Requests REQUESTS = new Requests(null);

    /**
     * Tries to acquire an access token from Toornament with the given scope.
     *
     * @param scopes The scopes of the token
     * @return A valid token or null if an error occurred.
     */
    public static Token getAccessToken(String clientID, String clientSecret, Set<Scope> scopes) {
        StringBuilder scopeString = new StringBuilder();
        for (Scope scope : scopes) {
            scopeString.append(scope.getInternalName()).append(" ");
        }

        List<BasicNameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("grant_type", "client_credentials"));
        payload.add(new BasicNameValuePair("client_id", clientID));
        payload.add(new BasicNameValuePair("client_secret", clientSecret));
        payload.add(new BasicNameValuePair("scope", scopeString.toString()));

        return ToornamentUtils.getTokenFromJSON(REQUESTS.POST.makeRequest(Endpoints.CRED_AUTH, URLEncodedUtils.format(payload, "UTF-8"),
                TokenObject.class, new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded")));
    }

    /**
     * Tries to refresh the given token. If the token has a refresh token it will use this,
     * otherwise it tries to get a new token with the same scope. <br>
     * <br>
     * <b>NOTE:</b> If the token has a refresh token but it has already expired, there is no way to
     * refresh the token without the user giving the permissions of the old token to give them again.
     *
     * @param token The token to refresh.
     * @return A refreshed token or null if not successful.
     */
    public static Token refreshToken(String clientID, String clientSecret, Token token) {
        if (token.getRefreshToken() != null) {
            if (token.hasRefreshExpired()) {
                return null;
            }

            List<BasicNameValuePair> payload = new ArrayList<>();
            payload.add(new BasicNameValuePair("grant_type", "client_credentials"));
            payload.add(new BasicNameValuePair("client_id", clientID));
            payload.add(new BasicNameValuePair("client_secret", clientSecret));
            payload.add(new BasicNameValuePair("refresh_token", token.getRefreshToken()));

            return ToornamentUtils.getTokenFromJSON(REQUESTS.POST.makeRequest(Endpoints.CRED_AUTH, URLEncodedUtils.format(payload, "UTF-8"),
                    TokenObject.class, new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded")));
        } else {
            return getAccessToken(clientID, clientSecret, token.getScope());
        }
    }
}
