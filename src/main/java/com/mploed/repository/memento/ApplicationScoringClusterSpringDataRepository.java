package com.mploed.repository.memento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationScoringClusterSpringDataRepository extends CrudRepository<ApplicationScoringClusterRecord, Long> {
    ApplicationScoringClusterRecord findByApplicationNumber(String antragsnummer);
}
