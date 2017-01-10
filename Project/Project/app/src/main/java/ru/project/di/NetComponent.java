package ru.project.di;

import dagger.Component;
import ru.project.net.TimePadLoader;

@MyScope
@Component(dependencies = ClientComponent.class, modules = {NetModule.class})
public interface NetComponent {
    void inject(TimePadLoader timePadLoader);
}
