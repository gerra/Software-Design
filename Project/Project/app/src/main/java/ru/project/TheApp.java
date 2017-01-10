package ru.project;

import android.app.Application;

import ru.project.di.DaggerNetComponent;
import ru.project.di.NetComponent;
import ru.project.net.TimePadLoader;

public class TheApp extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
//        netComponent = DaggerNetComponent.builder()
//                .netModule(new NetModule())
//                .build();

        netComponent = DaggerNetComponent.create();
        netComponent.inject(TimePadLoader.getInstance());
    }
}
