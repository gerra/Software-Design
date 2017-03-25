import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public abstract class ChildSearchActor extends UntypedActor {

    protected String hostname;

    public ChildSearchActor(String hostname) {
        if (hostname.startsWith("http")) {
            this.hostname = hostname;
        } else {
            this.hostname = "http://" + hostname;
        }
    }

    protected URL buildRequestUrl(String request) throws MalformedURLException {
        return new URL(hostname + "/" + request);
    }

    protected List<String> getUrlsFromIS(InputStream is) {
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            try {
                res.add(bis.readLine() + " (" + getClass().getSimpleName() + ")");
            } catch (IOException e) {
                return res;
            }
        }
        return res;
    }

    private List<String> getTop5Urls(ChildSearchRequest childSearchRequest) throws IOException {
        String requestQuery = childSearchRequest.getRequest();
        URLConnection connection = buildRequestUrl(requestQuery).openConnection();
        List<String> urls = getUrlsFromIS(connection.getInputStream());
        connection.getInputStream().close();
        return urls;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        System.out.println(self().path() + " " + o.getClass());
        if (o instanceof ChildSearchRequest) {
            ChildSearchRequest childSearchRequest = (ChildSearchRequest) o;
            List<String> top5Urls = getTop5Urls((ChildSearchRequest) o);
            ActorRef parent = getContext().parent();
            parent.tell(new SearchResponse(childSearchRequest.getChildName(), top5Urls), getSelf());
            getContext().stop(self());
        }
    }
}
