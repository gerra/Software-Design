package ru.project.mvp.events;

import java.util.List;

import ru.project.net.EventsRequest;
import ru.project.net.response.Event;
import rx.Observable;

public interface EventsModel {
    Observable<List<Event>> getEvents(EventsRequest eventsRequest);
}
