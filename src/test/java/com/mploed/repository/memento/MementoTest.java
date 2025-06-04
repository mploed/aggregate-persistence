package com.mploed.repository.mongodb;

import com.mploed.aggregate.memento.ApplicantScoringClusterMemento;
import com.mploed.aggregate.memento.ApplicationNumberMemento;
import com.mploed.aggregate.mongodb.ApplicantScoringClusterMongoDb;
import com.mploed.aggregate.mongodb.ApplicationNumberMongoDb;
import com.mploed.repository.memento.ApplicationScoringClusterJDBCRespository;
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
public class MementoTest {

    @Autowired
    private ApplicationScoringClusterJDBCRespository repo;

    @Test
    void testSave() {
        ApplicantScoringClusterMemento applicantScoringClusterMemento = new ApplicantScoringClusterMemento(new ApplicationNumberMemento("152"));
        applicantScoringClusterMemento.addCity("Berlin");
        repo.save(applicantScoringClusterMemento);

        ApplicantScoringClusterMemento loaded = repo.load(new ApplicationNumberMemento("152"));
        assertEquals(new ApplicationNumberMemento(("152")), loaded.applicationNumber());
    }

}