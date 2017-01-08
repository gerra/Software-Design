package ru.project.net;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.project.model.Event;
import ru.project.mvp.EventsModel;
import rx.Observable;

public class TimePadLoader implements EventsModel {

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
    public Observable<List<Event>> getEvents(String cities, int count, int offset) {
        return timePadService.getEvents(count, offset, cities);
    }
}
