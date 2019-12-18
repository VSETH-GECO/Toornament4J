package ch.ethz.geco.t4j.util;

public class Toornament4JException extends RuntimeException {
    private String message;

    public Toornament4JException(String message) {
        super(message);
        this.message = message;
    }

    public Toornament4JException(String message, Throwable cause) {
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
