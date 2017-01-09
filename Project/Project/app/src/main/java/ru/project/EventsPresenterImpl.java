package ru.project;

import android.util.Log;

import java.util.List;

import ru.project.mvp.events.EventsModel;
import ru.project.mvp.events.EventsPresenter;
import ru.project.mvp.events.EventsView;
import ru.project.net.EventsRequest;
import ru.project.net.response.Event;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventsPresenterImpl implements EventsPresenter {
    private static final String TAG = EventsPresenterImpl.class.getSimpleName();

    private EventsView eventsView;
    private EventsModel eventsModel;

    private Subscription eventsSubscription;
    private boolean isLoading = false;

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

    private void loadEvents(EventsRequest eventsRequest, boolean isLoadMore) {
        Log.d(TAG, "loadEvents()" + " isLoading = " + isLoading);
        if (isLoading) {
            return;
        }
        isLoading = true;
        if (!isLoadMore) {
            eventsView.showMainProgressBar();
        }
        eventsSubscription = eventsModel
                .getEvents(eventsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> isLoading = false)
                .subscribe(new Subscriber<List<Event>>() {
                    @Override
                    public void onCompleted() {
                        if (!isLoadMore) {
                            eventsView.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                        if (!isLoadMore) {
                            eventsView.hideProgress();
                        }
                    }

                    @Override
                    public void onNext(List<Event> events) {
                        eventsView.addEvents(events, eventsRequest);
                    }
                });
    }

    @Override
    public void loadEvents(EventsRequest eventsRequest) {
        loadEvents(eventsRequest, false);
    }

    @Override
    public void onEventClick(Event event) {

    }

    @Override
    public void onBottom(int currentEventsCount, EventsRequest lastRequest) {
        Log.d(TAG, "onBottom()");
        EventsRequest eventsRequest = new EventsRequest.Builder()
                .copyFrom(lastRequest)
                .setOffset(currentEventsCount)
                .build();
        loadEvents(eventsRequest, true);
    }
}
