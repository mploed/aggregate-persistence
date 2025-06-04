package com.mploed.repository.eventsourced;

import com.mploed.aggregate.eventsourced.events.DomainEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

@Repository
public class H2ApplicantScoringEventStore implements ApplicantScoringEventStore {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public H2ApplicantScoringEventStore(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void saveEvents(String applicationNumber, List<DomainEvent> events, Long expectedVersion) {
        // Check current version for optimistic concurrency control
        Long currentVersion = getCurrentVersion(applicationNumber);
        if (!Objects.equals(currentVersion, expectedVersion)) {
            throw new IllegalStateException("Expected version " + expectedVersion +
                    " but current version is " + currentVersion);
        }

        String sql = "INSERT INTO applicant_scoring_events (application_number, event_type, event_data, version, occurred_on) " +
                "VALUES (?, ?, ?, ?, ?)";

        for (DomainEvent event : events) {
            try {
                String eventData = objectMapper.writeValueAsString(event);
                jdbcTemplate.update(sql,
                        applicationNumber,
                        event.getClass().getSimpleName(),
                        eventData,
                        event.getVersion(),
                        event.getOccurredOn()
                );
            } catch (Exception e) {
                throw new RuntimeException("Failed to serialize event", e);
            }
        }
    }

    @Override
    public List<DomainEvent> getEventsForApplication(String applicationNumber) {
        String sql = "SELECT event_type, event_data FROM applicant_scoring_events WHERE application_number = ? ORDER BY version";

        return jdbcTemplate.query(sql,
                new Object[]{applicationNumber},
                (rs, rowNum) -> {
                    String eventType = rs.getString("event_type");
                    String eventData = rs.getString("event_data");

                    try {
                        Class<?> eventClass = Class.forName("com.mploed.aggregate.eventsourced.events." + eventType);
                        return (DomainEvent) objectMapper.readValue(eventData, eventClass);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to deserialize event", e);
                    }
                }
        );
    }

    private Long getCurrentVersion(String applicationNumber) {
        String sql = "SELECT COALESCE(MAX(version), 0) FROM applicant_scoring_events WHERE application_number = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, applicationNumber);
    }
}
