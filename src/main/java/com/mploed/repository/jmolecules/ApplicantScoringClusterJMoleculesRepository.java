package com.mploed.repository.jmolecules;

import org.jmolecules.ddd.types.Repository;

import com.mploed.aggregate.jmolecules.ApplicantScoringCluster;
import com.mploed.aggregate.jmolecules.ApplicantScoringCluster.ClusterIdentifier;
import com.mploed.aggregate.jmolecules.ApplicationNumber;

public interface ApplicantScoringClusterJMoleculesRepository extends Repository<ApplicantScoringCluster, ClusterIdentifier> {

	ApplicantScoringCluster save(ApplicantScoringCluster cluster);

	ApplicantScoringCluster findByApplicationNumber(ApplicationNumber antragsnummer);
}
