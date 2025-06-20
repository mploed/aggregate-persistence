package com.mploed.repository.relaxed;

import com.mploed.aggregate.relaxed.ApplicantScoringClusterRelaxed;
import com.mploed.aggregate.relaxed.ApplicationNumberRelaxed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ApplicationScoringClusterJDBCRelaxedRespository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationScoringClusterJDBCRelaxedRespository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(ApplicantScoringClusterRelaxed applicantScoringCluster) {
        String sql = "INSERT INTO scoring_applicant_cluster_relaxed (application_number, city, balance_at_bank) " +
                "VALUES (?, ?, ?)";

        jdbcTemplate.update(sql,
                applicantScoringCluster.applicationNumber().nummer(),
                applicantScoringCluster.getCity().getCity(),
                applicantScoringCluster.getBalance().getBalance()
        );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ApplicantScoringClusterRelaxed load(ApplicationNumberRelaxed applicationNumber) {
        String sql = "SELECT application_number, city, balance_at_bank FROM scoring_applicant_cluster_relaxed WHERE application_number = ?";
        ApplicantScoringClusterRelaxed result = null;
        return jdbcTemplate.query(sql, new Object[]{applicationNumber.nummer()}, rs -> {
            if (rs.next()) {
                ApplicantScoringClusterRelaxed cluster = new ApplicantScoringClusterRelaxed(applicationNumber);
                cluster.addCity(rs.getString("city"));
                cluster.addBalance(rs.getBigDecimal("balance_at_bank"));
                return cluster;
            } else {
                return null; // or throw an exception if not found
            }
        });
    }
}
