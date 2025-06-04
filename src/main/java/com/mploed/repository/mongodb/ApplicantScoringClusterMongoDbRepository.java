package com.mploed.repository.mongodb;

import com.mploed.aggregate.mongodb.ApplicantScoringClusterMongoDb;
import com.mploed.aggregate.mongodb.ApplicationNumberMongoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@org.jmolecules.ddd.annotation.Repository
public class ApplicantScoringClusterMongoDbRepository {
    private ApplicationScoringClusterSpringDataMongoRepository dao;

    @Autowired
    public ApplicantScoringClusterMongoDbRepository(ApplicationScoringClusterSpringDataMongoRepository dao) {
        this.dao = dao;
    }


    public void save(ApplicantScoringClusterMongoDb applicantScoringClusterMongoDb) {
        if(applicantScoringClusterMongoDb == null) {
            throw new IllegalArgumentException("Applicantcluster must not be null");
        }
        ApplicantScoringClusterDocument document = dao.findByApplicationNumber(applicantScoringClusterMongoDb.applicationNumber().nummer());
        if(document == null) {
            document = new ApplicantScoringClusterDocument();
            document.setApplicationNumber(applicantScoringClusterMongoDb.applicationNumber().nummer());
        }
        document.setApplicantScoringClusterMongoDb(applicantScoringClusterMongoDb);
        dao.save(document);
    }

    public ApplicantScoringClusterMongoDb load(ApplicationNumberMongoDb antragsnummer) {
        if(antragsnummer == null) {
            throw new IllegalArgumentException("Application number must not be null");
        }
        ApplicantScoringClusterDocument document = dao.findByApplicationNumber(antragsnummer.nummer());
        if(document == null) {
            return null;
        } else {
            return document.getApplicantScoringClusterMongoDb();
        }
    }
}
