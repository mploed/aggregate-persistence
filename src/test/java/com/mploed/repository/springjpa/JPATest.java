package com.mploed.repository.springjpa;

import com.mploed.aggregate.springjpa.ApplicantScoringClusterJPA;
import com.mploed.aggregate.springjpa.ApplicationNumberJPA;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JPATest {

    @Autowired
    private ApplicantScoringClusterJpaRepository repo;

    @Test
    void testSave() {
        ApplicantScoringClusterJPA applicantScoringCluster = new ApplicantScoringClusterJPA(new ApplicationNumberJPA("152"));
        applicantScoringCluster.addCity("Berlin");
        repo.save(applicantScoringCluster);

        ApplicantScoringClusterJPA loaded = repo.load(new ApplicationNumberJPA("152"));
        assertEquals(new ApplicationNumberJPA(("152")), loaded.applicationNumber());
    }

}