package ru.project.net;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.project.model.Event;
import rx.Observable;

public interface TimePadService {
    @GET("v1/events")
    Observable<List<Event>> getEvents(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("cities") String cities);

    @GET("v1/events/{event_id}")
    Observable<Event> getEventInfo(
            @Path("event_id") String eventId);
}
