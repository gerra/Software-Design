package ru.project;

import android.app.Application;

import ru.project.di.InjectorHelper;

public class TheApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InjectorHelper.buildStandard();
    }
}
