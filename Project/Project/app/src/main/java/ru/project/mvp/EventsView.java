package ru.project.mvp;

import java.util.List;

import ru.project.net.response.Event;

public interface EventsView {
    void showProgress();
    void hideProgress();
    void showEvents(List<Event> events);
}
