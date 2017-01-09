package ru.project.mvp.eventinfo;

public interface EventInfoPresenter {
    void loadEventInfo(int eventId);
    void onDestroy();
}
