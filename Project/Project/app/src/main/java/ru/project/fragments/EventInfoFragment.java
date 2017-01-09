package ru.project.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.project.EventInfoPresenterImpl;
import ru.project.R;
import ru.project.data.EventsSupplier;
import ru.project.mvp.eventinfo.EventInfoPresenter;
import ru.project.mvp.eventinfo.EventInfoView;
import ru.project.net.response.EventInfo;

public class EventInfoFragment extends Fragment implements EventInfoView {
    public static final String TAG = EventInfoFragment.class.getSimpleName();
    private static final String EVENT_ID_KEY = "EVENT_ID";
    private static final String EVENT_NAME_KEY = "EVENT_NAME";

    public static EventInfoFragment factory(int eventId, String eventName) {
        Bundle args = new Bundle();
        args.putInt(EVENT_ID_KEY, eventId);
        args.putString(EVENT_NAME_KEY, eventName);
        EventInfoFragment eventInfoFragment = new EventInfoFragment();
        eventInfoFragment.setArguments(args);
        return eventInfoFragment;
    }

    private int eventId;
    private String eventName;

    private View progressView;
    private View contentView;
    private ImageView eventPhotoView;
    private TextView eventNameView;
    private TextView eventDescriptionView;

    private EventInfoPresenter eventInfoPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventId = getArguments().getInt(EVENT_ID_KEY);
        eventName = getArguments().getString(EVENT_NAME_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressView = view.findViewById(R.id.progressBar);
        contentView = view.findViewById(R.id.content);
        eventPhotoView = (ImageView) view.findViewById(R.id.event_photo);
        eventNameView = (TextView) view.findViewById(R.id.event_name);
        eventDescriptionView = (TextView) view.findViewById(R.id.event_description);
        eventDescriptionView.setMovementMethod(LinkMovementMethod.getInstance());

        eventInfoPresenter = new EventInfoPresenterImpl(this, EventsSupplier.getInstance());
        eventInfoPresenter.loadEventInfo(eventId);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(eventName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        eventInfoPresenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEventInfo(EventInfo eventInfo) {
        Picasso.with(getContext())
                .load(eventInfo.getPosterImage().getUploadcareUrl())
                .fit()
                .into(eventPhotoView);

        String name = eventInfo.getName();
        name = name.replaceAll("&quot;", "\"");
        eventNameView.setText(name);

        String description = eventInfo.getDescriptionHtml();
        description = description.replaceAll("&quot;", "'");
        if (Build.VERSION.SDK_INT >= 24) {
            eventDescriptionView.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY));
        } else {
            eventDescriptionView.setText(Html.fromHtml(description));
        }
    }
}
