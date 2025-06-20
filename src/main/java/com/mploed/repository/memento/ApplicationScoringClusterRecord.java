package com.mploed.repository.memento;

import com.mploed.aggregate.memento.ApplicantScoringClusterMemento;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("SCORING_APPLICANT_CLUSTER_SPRING_JDBC")
public class ApplicationScoringClusterRecord {
    @Id
    private Long id;

    private String applicationNumber;

    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private ApplicantScoringClusterMemento.ApplicantClusterMemento memento;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public ApplicantScoringClusterMemento.ApplicantClusterMemento getMemento() {
        return memento;
    }

    public void setMemento(ApplicantScoringClusterMemento.ApplicantClusterMemento memento) {
        this.memento = memento;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
