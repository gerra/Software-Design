package ru.project.data;

import java.util.List;

import ru.project.model.Event;
import ru.project.mvp.EventsModel;
import ru.project.net.TimePadLoader;
import rx.Observable;

public class EventsSupplier implements EventsModel {

    private static class EventsSupplierHolder {
        private static EventsSupplier instance = new EventsSupplier();
    }

    public static EventsSupplier getInstance() {
        return EventsSupplierHolder.instance;
    }

    @Override
    public Observable<List<Event>> getEvents(String cities, int count, int offset) {
        return TimePadLoader.getInstance().getEvents(cities, count, offset);
    }
}
