package com.mploed.aggregate.springjpa;

import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.math.BigDecimal;
import java.util.Objects;


@AggregateRoot
@Entity
@Table(name = "applicant_scoring_cluster_jpa")
public class ApplicantScoringClusterJPA {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private ApplicationNumberJPA applicationNumber;

    @Embedded
    private CityJPA city;

    @Embedded
    private BalanceAtBankJPA balance;


    private ApplicantScoringClusterJPA() {
    }

    public ApplicantScoringClusterJPA(ApplicationNumberJPA applicationNumber) {
        if(applicationNumber == null) {
            throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
        }
        this.applicationNumber = applicationNumber;
        this.balance = new BalanceAtBankJPA(0);
    }

    public PointsJPA score() {
        if(city == null) {
            return null;
        }
        if (balance == null) {
            return null;        }

        PointsJPA result = new PointsJPA(0);
        result = result.plus(city.calculatePoints());
        result = result.plus(balance.calculatePoints());

        return result;
    }

    public ApplicationNumberJPA applicationNumber() {
        return applicationNumber;
    }

    public void addCity(String wohnort) {
        this.city = new CityJPA(wohnort);
    }

    public void addBalance(BigDecimal balance) {
        this.balance = new BalanceAtBankJPA(balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantScoringClusterJPA that = (ApplicantScoringClusterJPA) o;
        return Objects.equals(applicationNumber, that.applicationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumber);
    }
}
