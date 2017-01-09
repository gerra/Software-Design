package ru.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.project.fragments.EventInfoFragment;
import ru.project.fragments.EventsFragment;
import ru.project.net.response.Event;

public class MainActivity extends AppCompatActivity implements OnEventClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new EventsFragment(), EventsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onEventClick(Event event) {
        int id = event.getId();
        EventInfoFragment fragment = EventInfoFragment.factory(id);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, EventInfoFragment.TAG + id)
                .addToBackStack(null)
                .commit();
    }
}
