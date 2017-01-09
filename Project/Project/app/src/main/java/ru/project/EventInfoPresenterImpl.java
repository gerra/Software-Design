package ru.project;

import ru.project.mvp.eventinfo.EventInfoModel;
import ru.project.mvp.eventinfo.EventInfoPresenter;
import ru.project.mvp.eventinfo.EventInfoView;
import ru.project.net.response.EventInfo;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventInfoPresenterImpl implements EventInfoPresenter {

    private EventInfoView eventInfoView;
    private EventInfoModel eventInfoModel;
    private Subscription eventInfoSubscription;

    public EventInfoPresenterImpl(EventInfoView eventInfoView, EventInfoModel eventInfoModel) {
        this.eventInfoView = eventInfoView;
        this.eventInfoModel = eventInfoModel;
    }

    @Override
    public void loadEventInfo(int eventId) {
        eventInfoSubscription = eventInfoModel.getEventInfo(eventId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EventInfo>() {
                    @Override
                    public void onCompleted() {
                        eventInfoView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        eventInfoView.hideProgress();
                    }

                    @Override
                    public void onNext(EventInfo eventInfo) {
                        eventInfoView.showEventInfo(eventInfo);
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (eventInfoSubscription != null) {
            eventInfoSubscription.unsubscribe();
            eventInfoSubscription = null;
        }
        eventInfoView = null;
        eventInfoModel = null;
    }
}
