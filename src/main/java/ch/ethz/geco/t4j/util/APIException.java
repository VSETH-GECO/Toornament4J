package ch.ethz.geco.t4j.util;

import ch.ethz.geco.t4j.Toornament4J;

/**
 * A GECo API exception. Use the {@link APIException#getError()} method to get
 * more information about the error.
 */
public class APIException extends RuntimeException {
    private final Error error;

    public APIException(String message, Integer code) {
        super(message);

        switch (code) {
            case 404:
                this.error = Error.NOT_FOUND;
                break;
            case 403:
                this.error = Error.FORBIDDEN;
                break;
            case 424:
                this.error = Error.FAILED_DEPENDENCY;
                break;
            default:
                Toornament4J.LOGGER.error(LogMarkers.API, "Invalid error code: {}", code);
                this.error = null;
        }
    }

    /**
     * Gets the error code.
     *
     * @return The error code.
     */
    public Error getError() {
        return error;
    }

    public enum Error {NOT_FOUND, FORBIDDEN, FAILED_DEPENDENCY}
}