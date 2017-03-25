import com.sun.net.httpserver.HttpExchange;

/**
 * Created by german on 25.03.17.
 */
public class MasterSearchRequest extends SearchRequest {
    private long timeoutInMillis;
    private HttpExchange httpExchange;

    public MasterSearchRequest(long timeoutInMillis, HttpExchange httpExchange, String request) {
        super(request);
        this.timeoutInMillis = timeoutInMillis;
        this.httpExchange = httpExchange;
    }

    public long getTimeoutInMillis() {
        return timeoutInMillis;
    }

    public HttpExchange getHttpExchange() {
        return httpExchange;
    }
}
