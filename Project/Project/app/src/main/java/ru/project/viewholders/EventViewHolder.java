package ru.project.viewholders;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.project.R;
import ru.project.net.response.Event;
import ru.project.net.response.PosterImage;

public class EventViewHolder extends BaseViewHolder {
    private TextView nameView;
    private TextView timeView;
    private ImageView photoView;

    public EventViewHolder(View itemView) {
        super(itemView);
        nameView = (TextView) itemView.findViewById(R.id.event_name);
        timeView = (TextView) itemView.findViewById(R.id.event_time);
        photoView = (ImageView) itemView.findViewById(R.id.event_photo);
    }

    public void setData(Event event) {
        nameView.setText(event.getName());

        String timeAsStr = event.getStartsAt();
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        try {
            Date time = sourceDateFormat.parse(timeAsStr);
            SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd MMMM, HH:mm", Locale.getDefault());
            timeAsStr = targetDateFormat.format(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeView.setText(timeAsStr);

        PosterImage posterImage = event.getPosterImage();
        if (posterImage != null) {
            Log.d(getClass().getSimpleName(), posterImage.getDefaultUrl());
            Picasso.with(itemView.getContext())
                    .load(posterImage.getDefaultUrl())
                    .into(photoView);
        } else {
            Log.d(getClass().getSimpleName(), "null");
        }
    }
}
