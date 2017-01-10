package ru.project.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.project.net.TimePadLoader;

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(TimePadLoader timePadLoader);
}
