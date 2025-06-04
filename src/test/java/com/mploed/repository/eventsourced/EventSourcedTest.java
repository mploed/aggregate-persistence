package com.mploed.repository.eventsourced;
import com.mploed.aggregate.eventsourced.ApplicantSocringClusterEventSourced;
import com.mploed.aggregate.eventsourced.ApplicationNumberEventSourced;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventSourcedTest {
    @Autowired
    private EventSourcedApplicantScoringRepositoryEventSourced repo;

    @Test
    void testSave() {
        ApplicantSocringClusterEventSourced applicantScoringCluster = ApplicantSocringClusterEventSourced.create(new ApplicationNumberEventSourced("152"));
        applicantScoringCluster.addCity("Berlin");
        repo.save(applicantScoringCluster);

        ApplicantSocringClusterEventSourced loaded = repo.load(new ApplicationNumberEventSourced("152"))
                .orElseThrow(() -> new RuntimeException("ApplicantScoringCluster not found"));
        assertEquals(new ApplicationNumberEventSourced(("152")), loaded.applicationNumber());

        loaded.addBalance(new BigDecimal(20000));
        loaded.score();
        repo.save(loaded);
    }
}
