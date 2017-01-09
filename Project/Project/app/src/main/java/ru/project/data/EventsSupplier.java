package ru.project.data;

import java.util.List;

import ru.project.mvp.eventinfo.EventInfoModel;
import ru.project.mvp.events.EventsModel;
import ru.project.net.EventsRequest;
import ru.project.net.TimePadLoader;
import ru.project.net.response.Event;
import ru.project.net.response.EventInfo;
import rx.Observable;

public class EventsSupplier implements EventsModel, EventInfoModel {

    private static class EventsSupplierHolder {
        private static EventsSupplier instance = new EventsSupplier();
    }

    public static EventsSupplier getInstance() {
        return EventsSupplierHolder.instance;
    }

    @Override
    public Observable<List<Event>> getEvents(EventsRequest eventsRequest) {
        return TimePadLoader.getInstance().getEvents(eventsRequest);
    }

    @Override
    public Observable<EventInfo> getEventInfo(int eventId) {
        return TimePadLoader.getInstance().getEventInfo(eventId);
    }
}
