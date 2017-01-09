package ru.project.mvp.eventinfo;

import ru.project.net.response.EventInfo;

public interface EventInfoView {
    void showProgress();
    void hideProgress();
    void showEventInfo(EventInfo eventInfo);
}
