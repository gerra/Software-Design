package ru.project.mvp.events;

import ru.project.OnEventClickListener;
import ru.project.net.EventsRequest;

public interface EventsPresenter extends OnEventClickListener {
    void onDestroy();
    void loadEvents(EventsRequest eventsRequest);
    void onBottom(int currentEventsCount, EventsRequest lastRequest);
}
