package com.mploed.aggregate.mongodb;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.math.BigDecimal;
import java.util.Objects;


@AggregateRoot
public class ApplicantScoringClusterMongoDb {
    @Identity
    private final ApplicationNumberMongoDb applicationNumberMongoDb;

    private CityMongoDb city;
    private BalanceAtBankMongoDb balance;


    public ApplicantScoringClusterMongoDb(ApplicationNumberMongoDb applicationNumberMongoDb) {
        if(applicationNumberMongoDb == null) {
            throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
        }
        this.applicationNumberMongoDb = applicationNumberMongoDb;
        this.balance = new BalanceAtBankMongoDb(0);
    }

    public PointsMongoDb score() {
        if(city == null) {
            return null;
        }
        if (balance == null) {
            return null;        }

        PointsMongoDb result = new PointsMongoDb(0);
        result = result.plus(city.calculatePoints());
        result = result.plus(balance.calculatePoints());

        return result;
    }

    public ApplicationNumberMongoDb applicationNumber() {
        return applicationNumberMongoDb;
    }

    public void addCity(String wohnort) {
        this.city = new CityMongoDb(wohnort);
    }

    public void addBalance(BigDecimal balance) {
        this.balance = new BalanceAtBankMongoDb(balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantScoringClusterMongoDb that = (ApplicantScoringClusterMongoDb) o;
        return Objects.equals(applicationNumberMongoDb, that.applicationNumberMongoDb);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumberMongoDb);
    }
}
