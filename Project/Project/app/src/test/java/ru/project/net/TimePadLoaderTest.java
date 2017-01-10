package ru.project.net;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import ru.project.di.ClientModule;
import ru.project.di.InjectorHelper;
import ru.project.net.response.Event;
import ru.project.net.response.EventInfo;
import rx.Observable;
import rx.observers.TestSubscriber;

public class TimePadLoaderTest {

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        InjectorHelper.buildMock(server.url("/").toString(), new MockClientModule());
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void getInstance() throws Exception {
        TimePadLoader timePadLoader = TimePadLoader.getInstance();
        Assert.assertNotNull(timePadLoader);
        TimePadLoader timePadLoader2 = TimePadLoader.getInstance();
        Assert.assertEquals(timePadLoader, timePadLoader2);
    }

    @Test
    public void getEvents_5() throws Exception {
        TimePadLoader timePadLoader = TimePadLoader.getInstance();

        InputStream is = getClass().getClassLoader().getResourceAsStream("events_5");
        server.enqueue(new MockResponse().setBody(new Buffer().readFrom(is)));

        EventsRequest eventsRequest = new EventsRequest.Builder()
                .setCities("Москва")
                .setOffset(0)
                .setCount(5)
                .build();
        TestSubscriber<Event> subscriber = new TestSubscriber<>();
        timePadLoader.getEvents(eventsRequest).concatMap(Observable::from).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValueCount(5);
    }

    @Test
    public void getEvents_20() throws Exception {
        TimePadLoader timePadLoader = TimePadLoader.getInstance();

        InputStream is = getClass().getClassLoader().getResourceAsStream("events_20");
        server.enqueue(new MockResponse().setBody(new Buffer().readFrom(is)));

        EventsRequest eventsRequest = new EventsRequest.Builder()
                .setCities("Москва")
                .setOffset(0)
                .setCount(20)
                .build();
        TestSubscriber<Event> subscriber = new TestSubscriber<>();
        timePadLoader.getEvents(eventsRequest).concatMap(Observable::from).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValueCount(20);
    }

    @Test
    public void getEventInfo_424454() throws Exception {
        TimePadLoader timePadLoader = TimePadLoader.getInstance();

        InputStream is = getClass().getClassLoader().getResourceAsStream("event_info_424454");
        server.enqueue(new MockResponse().setBody(new Buffer().readFrom(is)));

        TestSubscriber<EventInfo> localSubscriber = new TestSubscriber<>();
        timePadLoader.getEventInfo(424454).subscribe(localSubscriber);
        localSubscriber.assertNoErrors();
        localSubscriber.assertValueCount(1);
    }

    @Test
    public void getEventInfo_424591() throws Exception {
        TimePadLoader timePadLoader = TimePadLoader.getInstance();

        InputStream is = getClass().getClassLoader().getResourceAsStream("event_info_424591");
        server.enqueue(new MockResponse().setBody(new Buffer().readFrom(is)));

        TestSubscriber<EventInfo> localSubscriber = new TestSubscriber<>();
        timePadLoader.getEventInfo(424591).subscribe(localSubscriber);
        localSubscriber.assertNoErrors();
        localSubscriber.assertValueCount(1);
    }

    @Module
    public static class MockClientModule extends ClientModule {
        @Override
        public OkHttpClient provideClient() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(0, TimeUnit.SECONDS)
                    .readTimeout(0, TimeUnit.SECONDS)
                    .writeTimeout(0, TimeUnit.SECONDS)
                    .build();
        }
    }
}