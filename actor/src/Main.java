import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final String GOOGLE_HOST = "0.0.0.0";
    public static final String YANDEX_HOST = "0.0.0.0";
    public static final String BING_HOST = "0.0.0.0";

    public static final int GOOGLE_PORT = 4000;
    public static final int YANDEX_PORT = 4001;
    public static final int BING_PORT = 4002;

    public static final String GOOGLE = GOOGLE_HOST + ":" + GOOGLE_PORT;
    public static final String YANDEX = YANDEX_HOST + ":" + YANDEX_PORT;
    public static final String BING = BING_HOST + ":" + BING_PORT;

    private static void printSamples(HttpExchange httpExchange, String prefix) throws IOException {
        System.err.println(prefix);
        if (httpExchange.getRequestURI().getPath().contains("to_" + prefix)) {
            System.out.println("pause " + prefix);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {}
        }
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(200, 0);
        for (char c = 'a'; c <= 't'; c++) {
            String s = prefix + "_" + c + ".com" + "\n";
            outputStream.write(s.getBytes());
        }
        httpExchange.close();
    }

    private static void startMockServer() throws IOException {
        HttpServer googleServer = HttpServer.create(new InetSocketAddress(GOOGLE_HOST, GOOGLE_PORT), 0);
        googleServer.createContext("/", httpExchange -> printSamples(httpExchange, "google"));
        googleServer.start();

        HttpServer yandexServer = HttpServer.create(new InetSocketAddress(YANDEX_HOST, YANDEX_PORT), 0);
        yandexServer.createContext("/", httpExchange -> printSamples(httpExchange, "yandex"));
        yandexServer.start();

        HttpServer bingServer = HttpServer.create(new InetSocketAddress(BING_HOST, BING_PORT), 0);
        bingServer.createContext("/", httpExchange -> printSamples(httpExchange, "bing"));
        bingServer.start();
    }

    private static int parentCount = 0;

    public static void main(String[] args) throws IOException {
        startMockServer();

        List<Class<?>> actorClasses = Arrays.asList(
                GoogleSearchActor.class,
                YandexSearchActor.class,
                BingSearchActor.class
        );

        ActorSystem system = ActorSystem.create("MySystem");
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", 4004), 0);
        httpServer.createContext("/", httpExchange -> {
            String query = httpExchange.getRequestURI().getPath().substring(1);
            System.out.println(query);
            Props masterProps = Props.create(MasterSearchActor.class, actorClasses);
//            Props.create(MasterSearchActor.class, actorClasses, 2000, httpExchange);
            ActorRef parent = system.actorOf(masterProps, "parent_" + parentCount++);
            parent.tell(new MasterSearchRequest(2000, httpExchange, query), ActorRef.noSender());
        });
        httpServer.start();
    }
}
