package com.mploed.aggregate.eventsourced.events;

public class ApplicantScoringClusterCreatedEvent extends DomainEvent {
    private String applicationNumber;


    private ApplicantScoringClusterCreatedEvent() {
        super(null, null);
    }

    public ApplicantScoringClusterCreatedEvent(String applicationNumber, Long version) {
        super(applicationNumber, version);
        this.applicationNumber = applicationNumber;
    }

    public String getApplicationNumber() { return applicationNumber; }
}