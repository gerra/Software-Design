/**
 * Created by german on 25.03.17.
 */
public class ChildSearchRequest extends SearchRequest {
    private String childName;

    public ChildSearchRequest(String childName, String request) {
        super(request);
        this.childName = childName;
    }

    public String getChildName() {
        return childName;
    }
}
