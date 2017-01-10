package ru.project.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import ru.project.di.InjectorHelper;
import ru.project.net.EventsRequest;
import ru.project.net.response.Event;
import ru.project.net.response.EventInfo;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertNotNull;

public class EventsSupplierTest {

    @Before
    public void setUp() throws Exception {
        InjectorHelper.buildStandard();
    }

    @Test
    public void getInstance() throws Exception {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();
        assertNotNull(eventsSupplier);
    }

    private void testEventsCount(int count) {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();

        EventsRequest eventsRequest = new EventsRequest.Builder()
                .setCities("Москва")
                .setOffset(0)
                .setCount(count)
                .build();
        TestSubscriber<Event> testSubscriber = new TestSubscriber<>();
        eventsSupplier.getEvents(eventsRequest).flatMap(Observable::from).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(count);
    }

    @Test
    public void getEvents_20() throws Exception {
        testEventsCount(20);
    }

    @Test
    public void getEvents_5() throws Exception {
        testEventsCount(5);
    }

    private void testGetEventInfoId(int id) {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();

        Observable<EventInfo> eventInfoObservable = eventsSupplier
                .getEventInfo(id);
        TestSubscriber<EventInfo> eventInfoSubscriber = new TestSubscriber<>();
        eventInfoObservable.subscribe(eventInfoSubscriber);
        eventInfoSubscriber.assertNoErrors();
        eventInfoSubscriber.assertValueCount(1);
        EventInfo eventInfo = eventInfoSubscriber.getOnNextEvents().get(0);

        Assert.assertEquals(eventInfo.getId(), id);
    }

    @Test
    public void getEventInfo_424454() {
        testGetEventInfoId(424454);
    }

    @Test
    public void getEventInfo_424591() {
        testGetEventInfoId(424591);
    }

    @Test
    public void getEventInfo_424591Compare() {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();

        InputStream is = getClass().getClassLoader().getResourceAsStream("event_info_424591");
        Gson gson = new Gson();
        EventInfo localEventInfo = gson.fromJson(new JsonReader(new InputStreamReader(is)), EventInfo.class);

        TestSubscriber<EventInfo> serverSubscriber = new TestSubscriber<>();
        eventsSupplier.getEventInfo(424591).subscribe(serverSubscriber);
        serverSubscriber.assertNoErrors();
        serverSubscriber.assertValueCount(1);
        EventInfo serverEventInfo = serverSubscriber.getOnNextEvents().get(0);

        Assert.assertEquals(localEventInfo.hashCode(), serverEventInfo.hashCode());
    }

    @Test
    public void getEventInfo_424454Compare() {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();

        InputStream is = getClass().getClassLoader().getResourceAsStream("event_info_424454");
        Gson gson = new Gson();
        EventInfo localEventInfo = gson.fromJson(new JsonReader(new InputStreamReader(is)), EventInfo.class);

        TestSubscriber<EventInfo> serverSubscriber = new TestSubscriber<>();
        eventsSupplier.getEventInfo(424454).subscribe(serverSubscriber);
        serverSubscriber.assertNoErrors();
        serverSubscriber.assertValueCount(1);
        EventInfo serverEventInfo = serverSubscriber.getOnNextEvents().get(0);

        Assert.assertEquals(localEventInfo, serverEventInfo);
    }

    @Test
    public void getEventInfo() throws Exception {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();

        EventsRequest eventsRequest = new EventsRequest.Builder()
                .setCities("Москва")
                .setOffset(0)
                .setCount(1)
                .build();
        Observable<Event> eventObservable = eventsSupplier
                .getEvents(eventsRequest)
                .flatMap(Observable::from)
                .first();
        TestSubscriber<Event> eventSubscriber = new TestSubscriber<>();
        eventObservable.subscribe(eventSubscriber);
        eventSubscriber.assertNoErrors();
        eventSubscriber.assertValueCount(1);
        Event event = eventSubscriber.getOnNextEvents().get(0);

        Observable<EventInfo> eventInfoObservable = eventsSupplier
                .getEventInfo(event.getId());
        TestSubscriber<EventInfo> eventInfoSubscriber = new TestSubscriber<>();
        eventInfoObservable.subscribe(eventInfoSubscriber);
        eventInfoSubscriber.assertNoErrors();
        eventInfoSubscriber.assertValueCount(1);
        EventInfo eventInfo = eventInfoSubscriber.getOnNextEvents().get(0);

        Assert.assertEquals(eventInfo.getId(), event.getId());
        Assert.assertEquals(eventInfo.getName().replaceAll("&quot;", "\""), event.getName().replaceAll("&quot;", "\""));
        Assert.assertEquals(eventInfo.getStartsAt(), event.getStartsAt());
        Assert.assertEquals(eventInfo.getCategories(), event.getCategories());
    }

}