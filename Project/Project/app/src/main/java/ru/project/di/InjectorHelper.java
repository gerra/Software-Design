package ru.project.di;

import ru.project.net.TimePadLoader;

public class InjectorHelper {
    public static void buildStandard() {
        ClientComponent clientComponent = DaggerClientComponent.builder()
                .clientModule(new ClientModule())
                .build();
        NetComponent netComponent = DaggerNetComponent.builder()
                .clientComponent(clientComponent)
                .netModule(new NetModule("https://api.timepad.ru/"))
                .build();
        netComponent.inject(TimePadLoader.getInstance());
    }

    public static void buildMock(String url, ClientModule mockClientModule) {
        ClientComponent clientComponent = DaggerClientComponent.builder()
                .clientModule(mockClientModule)
                .build();
        NetComponent netComponent = DaggerNetComponent.builder()
                .clientComponent(clientComponent)
                .netModule(new NetModule(url))
                .build();
        netComponent.inject(TimePadLoader.getInstance());
    }
}
