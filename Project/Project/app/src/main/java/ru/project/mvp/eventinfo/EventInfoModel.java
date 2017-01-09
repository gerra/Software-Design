package ru.project.mvp.eventinfo;

import ru.project.net.response.EventInfo;
import rx.Observable;

public interface EventInfoModel {
    Observable<EventInfo> getEventInfo(int eventId);
}
