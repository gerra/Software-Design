package ru.project.net;

import java.util.List;

import javax.inject.Inject;

import ru.project.mvp.eventinfo.EventInfoModel;
import ru.project.mvp.events.EventsModel;
import ru.project.net.response.Event;
import ru.project.net.response.EventInfo;
import ru.project.net.response.EventsResponse;
import rx.Observable;

public class TimePadLoader implements EventsModel, EventInfoModel {

    private static class TimePadLoaderHolder {
        private static TimePadLoader timePadLoader = new TimePadLoader();
    }

    @Inject
    TimePadService timePadService;

    public static TimePadLoader getInstance() {
        return TimePadLoaderHolder.timePadLoader;
    }

    private TimePadLoader() {
    }

    @Override
    public Observable<List<Event>> getEvents(EventsRequest eventsRequest) {
        return timePadService.getEvents(eventsRequest).map(EventsResponse::getEvents);
    }

    @Override
    public Observable<EventInfo> getEventInfo(int eventId) {
        return timePadService.getEventInfo(eventId);
    }
}
