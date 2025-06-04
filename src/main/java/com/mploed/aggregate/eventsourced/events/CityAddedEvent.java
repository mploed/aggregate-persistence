package com.mploed.aggregate.eventsourced.events;

public class CityAddedEvent extends DomainEvent {
    private String city;

    private CityAddedEvent() {
        super(null, null);
    }

    public CityAddedEvent(String applicationNumber, Long version, String city) {
        super(applicationNumber, version);
        this.city = city;
    }

    public String getCity() { return city; }
}