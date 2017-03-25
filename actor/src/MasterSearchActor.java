import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import com.sun.net.httpserver.HttpExchange;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MasterSearchActor extends UntypedActor {

    private HttpExchange httpExchange;
    private List<Class<?>> searchClasses;

    private List<List<String>> receivedResponses;

    public MasterSearchActor(List<Class<?>> classes) {
        this.searchClasses = new ArrayList<>(classes);
        this.receivedResponses = new ArrayList<>();
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(3, Duration.Zero(),
                DeciderBuilder.match(IOException.class, e -> OneForOneStrategy.restart())
                        .build());
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        System.out.println(self().path() + " " + o.getClass());
        if (o instanceof MasterSearchRequest) {
            MasterSearchRequest searchRequest = (MasterSearchRequest) o;
            httpExchange = searchRequest.getHttpExchange();
            long timeoutInMillis = searchRequest.getTimeoutInMillis();
            int childCount = 0;
            for (Class<?> searchClass : searchClasses) {
                String name = "child" + childCount++;
                ActorRef actorRef = getContext().actorOf(Props.create(searchClass), name);
                ChildSearchRequest childSearchRequest = new ChildSearchRequest(name, searchRequest.getRequest());
                actorRef.tell(childSearchRequest, getSelf());
            }
            getContext().setReceiveTimeout(Duration.create(timeoutInMillis, TimeUnit.MILLISECONDS));
        } else if (o instanceof SearchResponse) {
            receivedResponses.add(((SearchResponse) o).getTop5Urls());
            if (receivedResponses.size() == searchClasses.size()) {
                List<String> allUrs = receivedResponses.stream()
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                String asString = Arrays.toString(allUrs.toArray());
                System.out.println(asString);
                try {
                    httpExchange.sendResponseHeaders(200, 0);
                    httpExchange.getResponseBody().write(asString.getBytes());
                    httpExchange.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpExchange.close();
                getContext().stop(self());
            }
        } else if (o instanceof ReceiveTimeout) {
            getContext().setReceiveTimeout(Duration.Undefined());
            try {
                httpExchange.sendResponseHeaders(504, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpExchange.close();
            getContext().stop(self());
        }
    }
}
