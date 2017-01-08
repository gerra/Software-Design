package ru.project.data;

import java.util.List;

import ru.project.model.Event;
import ru.project.mvp.EventsModel;
import rx.Observable;

public class RestClient implements EventsModel {

    private static class RestClientHolder {
        private static RestClient instance = new RestClient();
    }

    public static RestClient getInstance() {
        return RestClientHolder.instance;
    }

    @Override
    public Observable<List<Event>> getEvents() {
        return null;
    }
}
