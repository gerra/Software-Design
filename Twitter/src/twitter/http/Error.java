package twitter.http;

/**
 * Created by root on 20.10.16.
 */
public class Error extends Exception {
    private int statusCode;
    private String errorMessage;
    private String body;

    public Error(int responseCode, String errorMessage, String body) {
        super("Received " + responseCode + " (" + errorMessage + ")");
        this.statusCode = responseCode;
        this.errorMessage = errorMessage;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getBody() {
        return body;
    }
}
