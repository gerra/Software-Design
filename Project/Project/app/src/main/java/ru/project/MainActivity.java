package ru.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ru.project.fragments.EditTextDialogFragment;
import ru.project.fragments.EventInfoFragment;
import ru.project.fragments.EventsFragment;
import ru.project.net.response.Event;

public class MainActivity extends AppCompatActivity implements OnEventClickListener, EditTextDialogFragment.DialogClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            openCityEventsFragment("Санкт-Петербург");
        }

        getSupportFragmentManager().addOnBackStackChangedListener(this::invalidateOptionsMenu);
    }

    private void openCityEventsFragment(String city) {
        EventsFragment eventsFragment = EventsFragment.factory(city);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, eventsFragment, EventsFragment.TAG)
                .commit();
        setTitle(city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        boolean showChangeCityOptionsItem;
        showChangeCityOptionsItem = visibleFragment == null || visibleFragment instanceof EventsFragment;
        menu.findItem(R.id.menu_change_city).setVisible(showChangeCityOptionsItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_change_city) {
            EditTextDialogFragment fragment = EditTextDialogFragment.factory(getString(R.string.change_city));
            fragment.show(getSupportFragmentManager(), EditTextDialogFragment.TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEventClick(Event event) {
        int id = event.getId();
        EventInfoFragment fragment = EventInfoFragment.factory(id, event.getName());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, EventInfoFragment.TAG + id)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onPositiveClick(String text) {
        openCityEventsFragment(text);
    }
}
