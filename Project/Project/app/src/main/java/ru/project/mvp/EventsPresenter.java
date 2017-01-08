package ru.project.mvp;

public interface EventsPresenter {
    void onDestroy();
    void loadEvents(String cities, int count, int offset);
}
