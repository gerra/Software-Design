package ru.project.mvp;

import java.util.List;

import ru.project.model.Event;
import rx.Observable;

public interface EventsModel {
    Observable<List<Event>> getEvents(String cities, int count, int offset);
}
