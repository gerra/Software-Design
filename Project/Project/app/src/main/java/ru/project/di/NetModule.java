package ru.project.di;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.project.net.TimePadService;

@Module
public class NetModule {
//    @Provides
//    @Singleton
//    OkHttpClient provideClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        return new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//    }

    private String baseURL;

    public NetModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    @MyScope
    Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(baseURL)
                .build();
    }

    @Provides
    @MyScope
    public TimePadService provideTimePadService(Retrofit retrofit) {
        return retrofit.create(TimePadService.class);
    }
}
