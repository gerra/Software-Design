package ru.project.net;

import java.util.HashMap;

public class EventsRequest extends HashMap<String, Object> {

    public int getOffset() {
        if (containsKey("offset")) {
            return (int) get("offset");
        } else {
            return 0;
        }
    }

    public static class Builder {
        private EventsRequest eventsRequest = new EventsRequest();

        public Builder copyFrom(EventsRequest copyFrom) {
            eventsRequest.putAll(copyFrom);
            return this;
        }

        public Builder setCount(int count) {
            eventsRequest.put("limit", count);
            return this;
        }

        public Builder setOffset(int offset) {
            eventsRequest.put("skip", offset);
            return this;
        }

        public Builder setCities(String cities) {
            eventsRequest.put("cities", cities);
            return this;
        }

        public Builder setSortBy(boolean ascendant, String sortBy) {
            if (ascendant) {
                eventsRequest.put("sort", "+" + sortBy);
            } else {
                eventsRequest.put("sort", "-" + sortBy);
            }
            return this;
        }

        public EventsRequest build() {
            return eventsRequest;
        }
    }
}
