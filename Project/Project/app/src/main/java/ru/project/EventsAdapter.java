package ru.project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.project.net.response.Event;
import ru.project.viewholders.BaseViewHolder;
import ru.project.viewholders.EventViewHolder;
import ru.project.viewholders.ProgressBarViewHolder;

public class EventsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int EVENT_VIEW_TYPE = 0;
    private static final int PROGRESS_BAR_VIEW_TYPE = 1;

    private List<Event> events = new ArrayList<>();
    private boolean showProgressBar = false;

    private OnEventClickListener onEventClickListener;

    public EventsAdapter(OnEventClickListener onEventClickListener) {
        this.onEventClickListener = onEventClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EVENT_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
            return new EventViewHolder(view);
        } else if (viewType == PROGRESS_BAR_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
            return new ProgressBarViewHolder(view);
        } else {
            throw new IllegalArgumentException("Unknown viewType " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof EventViewHolder) {
            Event event = events.get(position);
            ((EventViewHolder) holder).setData(event);
            ((EventViewHolder) holder).itemView
                    .setOnClickListener(v -> onEventClickListener.onEventClick(event));
        } else if (holder instanceof ProgressBarViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return events.size() + (showProgressBar ? 1 : 0);
    }

    public void setEvents(List<Event> events) {
        if (events != null) {
            this.events = events;
            notifyDataSetChanged();
        }
    }

    public void setShowProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
    }

    public void addEvents(List<Event> events, int offset) {
        if (offset < this.events.size()) {
            int oldSize = this.events.size();
            this.events.subList(offset, oldSize).clear();
            notifyItemRangeRemoved(offset, oldSize - offset);
        }
        int oldSize = this.events.size();
        this.events.addAll(events);
        notifyItemRangeInserted(oldSize, events.size());
    }
}
