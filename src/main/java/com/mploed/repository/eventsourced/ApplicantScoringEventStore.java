package com.mploed.repository.eventsourced;

import com.mploed.aggregate.eventsourced.events.DomainEvent;

import java.util.List;

public interface ApplicantScoringEventStore {
    void saveEvents(String applicationNumber, List<DomainEvent> events, Long expectedVersion);
    List<DomainEvent> getEventsForApplication(String applicationNumber);
}