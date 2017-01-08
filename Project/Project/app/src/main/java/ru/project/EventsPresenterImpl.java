package ru.project;

import java.util.List;

import ru.project.mvp.EventsModel;
import ru.project.mvp.EventsPresenter;
import ru.project.mvp.EventsView;
import ru.project.net.response.Event;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventsPresenterImpl implements EventsPresenter {
    private EventsView eventsView;
    private EventsModel eventsModel;

    private Subscription eventsSubscription;

    public EventsPresenterImpl(EventsView eventsView, EventsModel eventsModel) {
        this.eventsView = eventsView;
        this.eventsModel = eventsModel;
    }


    @Override
    public void onDestroy() {
        if (eventsSubscription != null) {
            eventsSubscription.unsubscribe();
            eventsSubscription = null;
        }
        eventsModel = null;
        eventsView = null;
    }

    @Override
    public void loadEvents(String cities, int count, int offset) {
        eventsView.showProgress();
        eventsSubscription = eventsModel
                .getEvents(cities, count, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Event>>() {
            @Override
            public void onCompleted() {
                eventsView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                eventsView.hideProgress();
            }

            @Override
            public void onNext(List<Event> events) {
                eventsView.showEvents(events);
            }
        });
    }
}
