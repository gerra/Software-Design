/**
 * Created by german on 25.03.17.
 */
public abstract class SearchRequest {
    private String request;

    public SearchRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
