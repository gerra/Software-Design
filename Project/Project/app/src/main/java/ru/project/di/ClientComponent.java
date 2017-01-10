package ru.project.di;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = ClientModule.class)
public interface ClientComponent {
    OkHttpClient client();
}
