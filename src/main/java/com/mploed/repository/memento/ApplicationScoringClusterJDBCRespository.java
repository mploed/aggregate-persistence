package com.mploed.repository.memento;

import com.mploed.aggregate.memento.ApplicantScoringClusterMemento;
import com.mploed.aggregate.memento.ApplicationNumberMemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ApplicationScoringClusterJDBCRespository {
    private ApplicationScoringClusterSpringDataRepository dao;

    @Autowired
    public ApplicationScoringClusterJDBCRespository(ApplicationScoringClusterSpringDataRepository dao) {
        this.dao = dao;
    }


    public void save(ApplicantScoringClusterMemento applicantScoringCluster) {
        ApplicantScoringClusterMemento.ApplicantClusterMemento memento = applicantScoringCluster.memento();
        ApplicationScoringClusterRecord record = dao.findByApplicationNumber(applicantScoringCluster.applicationNumber().nummer());
        if(record == null) {
            record = new ApplicationScoringClusterRecord();
            record.setApplicationNumber(memento.applicationNumber());
        }
        record.setMemento(memento);
        dao.save(record);
    }


    public ApplicantScoringClusterMemento load(ApplicationNumberMemento applicationNumber) {
        if(applicationNumber == null) {
            throw new IllegalArgumentException("ApplicationNumber must not be null");
        }

        ApplicationScoringClusterRecord record = dao.findByApplicationNumber(applicationNumber.nummer());
        if(record == null) {
            return null;
        } else {
            return ApplicantScoringClusterMemento.fromMemento(record.getMemento());
        }
    }
}
