package com.mploed.repository.mongodb;

import com.mploed.aggregate.mongodb.ApplicantScoringClusterMongoDb;
import com.mploed.aggregate.mongodb.ApplicationNumberMongoDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
public class MongoDbTest {
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.7");
    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ApplicantScoringClusterMongoDbRepository repo;

    @Test
    void testSave() {
        ApplicantScoringClusterMongoDb applicantScoringClusterMongoDb = new ApplicantScoringClusterMongoDb(new ApplicationNumberMongoDb("152"));
        applicantScoringClusterMongoDb.addCity("Berlin");
        repo.save(applicantScoringClusterMongoDb);

        ApplicantScoringClusterMongoDb loaded = repo.load(new ApplicationNumberMongoDb("152"));
        assertEquals(new ApplicationNumberMongoDb(("152")), loaded.applicationNumber());
    }

}