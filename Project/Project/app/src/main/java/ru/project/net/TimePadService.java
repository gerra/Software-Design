package ru.project.net;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import ru.project.net.response.EventInfo;
import ru.project.net.response.EventsResponse;
import rx.Observable;

public interface TimePadService {
    @GET("v1/events")
    Observable<EventsResponse> getEvents(@QueryMap EventsRequest eventsRequest);

    @GET("v1/events/{event_id}")
    Observable<EventInfo> getEventInfo(@Path("event_id") int eventId);
}
