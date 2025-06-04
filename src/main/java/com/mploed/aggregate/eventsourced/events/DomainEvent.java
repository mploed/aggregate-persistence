package com.mploed.aggregate.eventsourced.events;

import java.time.Instant;
import java.util.Date;

public abstract class DomainEvent {
    private final String applicationNumber;
    private final Date occurredOn;
    private final Long version;

    protected DomainEvent(String applicationNumber, Long version) {
        this.applicationNumber = applicationNumber;
        this.version = version;
        this.occurredOn = new Date();
    }

    public String getApplicationNumber() { return applicationNumber; }
    public Date getOccurredOn() { return occurredOn; }
    public Long getVersion() { return version; }
}