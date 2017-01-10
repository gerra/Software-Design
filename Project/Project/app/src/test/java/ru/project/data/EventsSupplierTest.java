package ru.project.data;

import junit.framework.Assert;

import org.junit.Test;

import ru.project.net.EventsRequest;
import ru.project.net.response.Event;
import ru.project.net.response.EventInfo;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertNotNull;

public class EventsSupplierTest {
    @Test
    public void getInstance() throws Exception {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();
        assertNotNull(eventsSupplier);
    }

    @Test
    public void getEvents() throws Exception {
        EventsSupplier eventsSupplier = EventsSupplier.getInstance();

        EventsRequest eventsRequest = new EventsRequest.Builder()
                .setCities("Москва")
                .setOffset(0)
                .setCount(50)
                .build();
        TestSubscriber<Event> testSubscriber = new TestSubscriber<>();
        eventsSupplier.getEvents(eventsRequest).flatMap(Observable::from).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(50);
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