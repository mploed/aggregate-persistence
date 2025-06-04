package com.mploed.repository.springjpa;

import com.mploed.aggregate.springjpa.ApplicantScoringClusterJPA;
import com.mploed.aggregate.springjpa.ApplicationNumberJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicantScoringClusterJpaRepository {
    private ApplicantScoringClusterSpringDataRepository dao;

    @Autowired
    public ApplicantScoringClusterJpaRepository(ApplicantScoringClusterSpringDataRepository dao) {
        this.dao = dao;
    }


    public void save(ApplicantScoringClusterJPA applicantScoringClusterJPA) {
        dao.save(applicantScoringClusterJPA);
    }


    public ApplicantScoringClusterJPA load(ApplicationNumberJPA applicationNumber) {
        ApplicantScoringClusterJPA cluster = dao.findByApplicationNumber(applicationNumber);
        return cluster;
    }
}
