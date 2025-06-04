package com.mploed.repository.eventsourced;

import com.mploed.aggregate.eventsourced.ApplicantSocringClusterEventSourced;
import com.mploed.aggregate.eventsourced.ApplicationNumberEventSourced;
import com.mploed.aggregate.eventsourced.events.DomainEvent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EventSourcedApplicantScoringRepositoryEventSourced implements ApplicantScoringRepositoryEventSourced {

    private final ApplicantScoringEventStore eventStore;

    public EventSourcedApplicantScoringRepositoryEventSourced(ApplicantScoringEventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    @Transactional
    public void save(ApplicantSocringClusterEventSourced aggregate) {
        List<DomainEvent> uncommittedEvents = aggregate.getUncommittedEvents();
        if (!uncommittedEvents.isEmpty()) {
            Long expectedVersion = aggregate.getVersion() - uncommittedEvents.size();
            eventStore.saveEvents(
                    aggregate.applicationNumber().nummer(),
                    uncommittedEvents,
                    expectedVersion
            );
            aggregate.markEventsAsCommitted();
        }
    }

    @Override
    public Optional<ApplicantSocringClusterEventSourced> load(ApplicationNumberEventSourced applicationNumber) {
        List<DomainEvent> events = eventStore.getEventsForApplication(applicationNumber.nummer());
        if (events.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ApplicantSocringClusterEventSourced.fromEvents(events));
    }
}