package ru.project.net;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.project.mvp.eventinfo.EventInfoModel;
import ru.project.mvp.events.EventsModel;
import ru.project.net.response.Event;
import ru.project.net.response.EventInfo;
import ru.project.net.response.EventsResponse;
import rx.Observable;

public class TimePadLoader implements EventsModel, EventInfoModel {

    private static class TimePadLoaderHolder {
        private static TimePadLoader timePadLoader = new TimePadLoader();
    }

    private TimePadService timePadService;

    public static TimePadLoader getInstance() {
        return TimePadLoaderHolder.timePadLoader;
    }

    private TimePadLoader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://api.timepad.ru/")
                .build();
        timePadService = retrofit.create(TimePadService.class);
    }

    @Override
    public Observable<List<Event>> getEvents(EventsRequest eventsRequest) {
        return timePadService.getEvents(eventsRequest).map(EventsResponse::getEvents);
    }

    @Override
    public Observable<EventInfo> getEventInfo(int eventId) {
        return timePadService.getEventInfo(eventId);
    }
}
