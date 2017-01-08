package ru.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.project.fragments.EventsFragment;

public class MainActivity extends AppCompatActivity {

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
}
