package com.mploed.aggregate.eventsourced;

import com.mploed.aggregate.eventsourced.events.*;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AggregateRoot
public class ApplicantSocringClusterEventSourced {
    @Identity
    private ApplicationNumberEventSourced applicationNumberEventSourced;
    private CityEventSourced city;
    private BalanceAtBankEventSourced balance;
    private PointsEventSourced lastCalculatedScore;
    private Long version;
    private List<DomainEvent> uncommittedEvents;

    // Private constructor for event sourcing reconstruction
    private ApplicantSocringClusterEventSourced() {
        this.uncommittedEvents = new ArrayList<>();
        this.version = 0L;
    }

    // Factory method for creating new aggregate
    public static ApplicantSocringClusterEventSourced create(ApplicationNumberEventSourced applicationNumber) {
        if (applicationNumber == null) {
            throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
        }

        ApplicantSocringClusterEventSourced aggregate = new ApplicantSocringClusterEventSourced();

        ApplicantScoringClusterCreatedEvent event = new ApplicantScoringClusterCreatedEvent(
                applicationNumber.nummer(),
                aggregate.getNextVersion()
        );

        aggregate.apply(event);
        aggregate.uncommittedEvents.add(event);

        return aggregate;
    }

    // Factory method for reconstruction from events
    public static ApplicantSocringClusterEventSourced fromEvents(List<DomainEvent> events) {
        if (events.isEmpty()) {
            throw new IllegalArgumentException("Cannot reconstruct aggregate from empty event list");
        }

        ApplicantSocringClusterEventSourced aggregate = new ApplicantSocringClusterEventSourced();

        for (DomainEvent event : events) {
            aggregate.apply(event);
        }
        aggregate.markEventsAsCommitted();

        return aggregate;
    }

    // Business methods
    public void addCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }

        CityAddedEvent event = new CityAddedEvent(
                this.applicationNumberEventSourced.nummer(),
                getNextVersion(),
                city
        );

        apply(event);
        uncommittedEvents.add(event);
    }

    public void addBalance(BigDecimal balance) {
        if (balance == null) {
            throw new IllegalArgumentException("Balance cannot be null");
        }

        BalanceAddedEvent event = new BalanceAddedEvent(
                this.applicationNumberEventSourced.nummer(),
                getNextVersion(),
                balance
        );

        apply(event);
        uncommittedEvents.add(event);
    }

    public PointsEventSourced score() {
        if (city == null || balance == null) {
            return null;
        }

        PointsEventSourced cityPoints = city.calculatePoints();
        PointsEventSourced balancePoints = balance.calculatePoints();
        PointsEventSourced totalPoints = new PointsEventSourced(0).plus(cityPoints).plus(balancePoints);

        // Generate scoring calculated event
        ScoringCalculatedEvent event = new ScoringCalculatedEvent(
                this.applicationNumberEventSourced.nummer(),
                getNextVersion(),
                totalPoints.getPoints()
        );

        apply(event);
        uncommittedEvents.add(event);

        return totalPoints;
    }

    // Event application methods
    private void apply(DomainEvent event) {
        if (event instanceof ApplicantScoringClusterCreatedEvent) {
            apply((ApplicantScoringClusterCreatedEvent) event);
        } else if (event instanceof CityAddedEvent) {
            apply((CityAddedEvent) event);
        } else if (event instanceof BalanceAddedEvent) {
            apply((BalanceAddedEvent) event);
        } else if (event instanceof ScoringCalculatedEvent) {
            apply((ScoringCalculatedEvent) event);
        }
        this.version = event.getVersion();
    }

    private void apply(ApplicantScoringClusterCreatedEvent event) {
        this.applicationNumberEventSourced = new ApplicationNumberEventSourced(event.getApplicationNumber());
        this.balance = new BalanceAtBankEventSourced(0);
    }

    private void apply(CityAddedEvent event) {
        this.city = new CityEventSourced(event.getCity());
    }

    private void apply(BalanceAddedEvent event) {
        this.balance = new BalanceAtBankEventSourced(event.getBalance());
    }

    private void apply(ScoringCalculatedEvent event) {
        this.lastCalculatedScore = new PointsEventSourced(event.getPoints());
    }

    // Helper methods
    private Long getNextVersion() {
        this.version = this.version +1;
        return this.version;
    }

    public List<DomainEvent> getUncommittedEvents() {
        return new ArrayList<>(uncommittedEvents);
    }

    public void markEventsAsCommitted() {
        this.uncommittedEvents.clear();
    }

    // Getters
    public ApplicationNumberEventSourced applicationNumber() {
        return applicationNumberEventSourced;
    }

    public CityEventSourced getCity() {
        return city;
    }

    public BalanceAtBankEventSourced getBalance() {
        return balance;
    }

    public PointsEventSourced getLastCalculatedScore() {
        return lastCalculatedScore;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantSocringClusterEventSourced that = (ApplicantSocringClusterEventSourced) o;
        return Objects.equals(applicationNumberEventSourced, that.applicationNumberEventSourced);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumberEventSourced);
    }
}
