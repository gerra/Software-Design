package ru.project;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class OnBottomScrollListener extends RecyclerView.OnScrollListener {
    public interface OnBottomListener {
        void onBottom(int layoutManagerItemCount);
    }

    private int itemThreshold;
    private OnBottomListener onBottomListener;

    public OnBottomScrollListener(int itemThreshold, OnBottomListener onBottomListener) {
        this.itemThreshold = itemThreshold;
        this.onBottomListener = onBottomListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy < 0) {
            return;
        }
        int totalCount = recyclerView.getLayoutManager().getItemCount();
        int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        if (lastVisibleItem + itemThreshold >= totalCount) {
            if (onBottomListener != null) {
                onBottomListener.onBottom(totalCount);
            }
        }
    }
}
