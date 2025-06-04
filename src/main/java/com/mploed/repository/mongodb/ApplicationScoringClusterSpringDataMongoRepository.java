package com.mploed.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationScoringClusterSpringDataMongoRepository extends MongoRepository<ApplicantScoringClusterDocument, String> {
    ApplicantScoringClusterDocument findByApplicationNumber(String applicationNumber);
}
