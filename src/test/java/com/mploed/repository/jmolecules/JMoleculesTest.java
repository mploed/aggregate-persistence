package com.mploed.repository.jmolecules;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mploed.aggregate.jmolecules.ApplicantScoringCluster;
import com.mploed.aggregate.jmolecules.ApplicationNumber;

@SpringBootTest
class JMoleculesTest {

	@Autowired ApplicantScoringClusterJMoleculesRepository repo;

	@Test
	void testSave() {

		repo.save(new ApplicantScoringCluster(new ApplicationNumber("152")).addCity("Berlin"));

		var loaded = repo.findByApplicationNumber(new ApplicationNumber("152"));

		assertThat(loaded.applicationNumber()).isEqualTo(new ApplicationNumber("152"));
	}
}
