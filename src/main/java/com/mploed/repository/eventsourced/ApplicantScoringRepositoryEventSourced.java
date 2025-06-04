package com.mploed.repository.eventsourced;

import com.mploed.aggregate.eventsourced.ApplicantSocringClusterEventSourced;
import com.mploed.aggregate.eventsourced.ApplicationNumberEventSourced;

import java.util.Optional;

public interface ApplicantScoringRepositoryEventSourced {
    void save(ApplicantSocringClusterEventSourced aggregate);
    Optional<ApplicantSocringClusterEventSourced> load(ApplicationNumberEventSourced applicationNumber);
}