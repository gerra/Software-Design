import java.util.List;

/**
 * Created by german on 25.03.17.
 */
public class SearchResponse {
    private String childName;
    private List<String> top5Urls;

    public SearchResponse(String childName, List<String> top5Urls) {
        this.childName = childName;
        this.top5Urls = top5Urls;
    }

    public List<String> getTop5Urls() {
        return top5Urls;
    }
}
