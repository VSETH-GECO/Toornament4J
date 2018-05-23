package ch.ethz.geco.t4j.util;

public class ToornamentException extends RuntimeException {
    private String message;

    public ToornamentException(String message) {
        super(message);
        this.message = message;
    }

    public ToornamentException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    /**
     * Gets the error message.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return message;
    }
}
