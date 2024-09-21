package server.exception;

public class ChhalaangException extends RuntimeException {
    private String displayMessage;

    public ChhalaangException(String message) {
        super(message);
    }

    public ChhalaangException(Throwable cause) {
        super(cause);
    }

    public ChhalaangException(String message, String displayMessage) {
        super(message);
        this.displayMessage = displayMessage;
    }

    protected ChhalaangException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ChhalaangException(String message, String displayMessage, Throwable cause) {
        super(message, cause);
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}
