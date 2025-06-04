package com.mploed.repository.relaxed;

import com.mploed.aggregate.relaxed.ApplicantScoringClusterRelaxed;
import com.mploed.aggregate.relaxed.ApplicationNumberRelaxed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RelaxedTest {
    @Autowired
    private ApplicationScoringClusterJDBCRelaxedRespository repo;

    @Test
    void testSave() {
        ApplicantScoringClusterRelaxed applicantScoringClusterRelaxed = new ApplicantScoringClusterRelaxed(new ApplicationNumberRelaxed("123"));
        applicantScoringClusterRelaxed.addCity("Berlin");
        repo.save(applicantScoringClusterRelaxed);

        ApplicantScoringClusterRelaxed loaded = repo.load(new ApplicationNumberRelaxed("123"));
        assertEquals(new ApplicationNumberRelaxed(("123")), loaded.applicationNumber());
    }
}
