package ru.project.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.project.model.Event;
import ru.project.EventsAdapter;
import ru.project.mvp.EventsPresenter;
import ru.project.EventsPresenterImpl;
import ru.project.mvp.EventsView;
import ru.project.R;
import ru.project.data.RestClient;

public class EventsFragment extends Fragment implements EventsView {
    public static final String TAG = EventsFragment.class.getSimpleName();

    private EventsPresenter eventsPresenter;

    private View progressBarView;
    private RecyclerView eventsListView;
    private EventsAdapter eventsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventsAdapter = new EventsAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventsListView = (RecyclerView) view.findViewById(R.id.eventsList);
        progressBarView = view.findViewById(R.id.progressBar);

        eventsListView.setAdapter(eventsAdapter);
        eventsListView.setLayoutManager(new LinearLayoutManager(getContext()));

        eventsPresenter = new EventsPresenterImpl(this, RestClient.getInstance());
        eventsPresenter.loadEvents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        eventsPresenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBarView.setVisibility(View.VISIBLE);
        eventsListView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBarView.setVisibility(View.GONE);
        eventsListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEvents(List<Event> events) {
        eventsAdapter.setEvents(events);
    }
}
