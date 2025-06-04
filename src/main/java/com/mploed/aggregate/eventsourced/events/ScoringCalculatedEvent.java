package com.mploed.aggregate.eventsourced.events;

public class ScoringCalculatedEvent extends DomainEvent {
    private Integer points;

    private ScoringCalculatedEvent() {
        super(null, null);
    }

    public ScoringCalculatedEvent(String applicationNumber, Long version,
                                  Integer points) {
        super(applicationNumber, version);
        this.points = points;
    }

    public Integer getPoints() { return points; }
}