package com.mploed.repository.springjpa;

import com.mploed.aggregate.springjpa.ApplicantScoringClusterJPA;
import com.mploed.aggregate.springjpa.ApplicationNumberJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantScoringClusterSpringDataRepository extends JpaRepository<ApplicantScoringClusterJPA, Long> {
    ApplicantScoringClusterJPA findByApplicationNumber(ApplicationNumberJPA antragsnummer);
}
