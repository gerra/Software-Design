package ru.project.mvp.events;

import java.util.List;

import ru.project.net.EventsRequest;
import ru.project.net.response.Event;

public interface EventsView {
    void showMainProgressBar();
    void hideProgress();
    void showEvents(List<Event> events);
    void addEvents(List<Event> events, EventsRequest eventsRequest);
}
