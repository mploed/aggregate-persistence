package com.mploed.repository.mongodb;

import com.mploed.aggregate.mongodb.ApplicantScoringClusterMongoDb;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applicantScoringCluster")
public class ApplicantScoringClusterDocument {
    @Id
    private String id;

    @Indexed(unique = true)
    private String applicationNumber;

    private ApplicantScoringClusterMongoDb applicantScoringClusterMongoDb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public ApplicantScoringClusterMongoDb getApplicantScoringClusterMongoDb() {
        return applicantScoringClusterMongoDb;
    }

    public void setApplicantScoringClusterMongoDb(ApplicantScoringClusterMongoDb applicantScoringClusterMongoDb) {
        this.applicantScoringClusterMongoDb = applicantScoringClusterMongoDb;
    }
}
