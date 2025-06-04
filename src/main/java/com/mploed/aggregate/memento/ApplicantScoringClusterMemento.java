package com.mploed.aggregate.memento;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.math.BigDecimal;
import java.util.Objects;


@AggregateRoot
public class ApplicantScoringClusterMemento {
    @Identity
    private ApplicationNumberMemento applicationNumber;

    private CityMemento city;
    private BalanceAtBankMemento balance;


    public ApplicantScoringClusterMemento(ApplicationNumberMemento applicationNumberMongoDb) {
        if(applicationNumberMongoDb == null) {
            throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
        }
        this.applicationNumber = applicationNumberMongoDb;
        this.balance = new BalanceAtBankMemento(0);
    }
    public ApplicantScoringClusterMemento(ApplicantClusterMemento memento) {
        this.applicationNumber = new ApplicationNumberMemento(memento.applicationNumber());
        if(memento.city() == null) {
            this.city = null;
        } else {
            this.city = new CityMemento(memento.city());
        }

        if(memento.balanceAtBank == null) {
            this.balance = new BalanceAtBankMemento(0);
        } else {
            this.balance = new BalanceAtBankMemento(memento.balanceAtBank());
        }

    }

    public static ApplicantScoringClusterMemento fromMemento(ApplicantClusterMemento memento) {
        return new ApplicantScoringClusterMemento(memento);
    }

    public PointsMemento score() {
        if(city == null) {
            return null;
        }
        if (balance == null) {
            return null;        }

        PointsMemento result = new PointsMemento(0);
        result = result.plus(city.calculatePoints());
        result = result.plus(balance.calculatePoints());

        return result;
    }

    public ApplicationNumberMemento applicationNumber() {
        return applicationNumber;
    }

    public void addCity(String wohnort) {
        this.city = new CityMemento(wohnort);
    }

    public void addBalance(BigDecimal balance) {
        this.balance = new BalanceAtBankMemento(balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantScoringClusterMemento that = (ApplicantScoringClusterMemento) o;
        return Objects.equals(applicationNumber, that.applicationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumber);
    }

    public ApplicantClusterMemento memento() {
        String city = this.city == null ? null : this.city.city();
        BigDecimal balanceAtBank = this.balance == null ? null : this.balance.balance();
        return new ApplicantClusterMemento(applicationNumber().nummer(), city, balanceAtBank);
    }
    public record ApplicantClusterMemento(String applicationNumber, String city, BigDecimal balanceAtBank) {

    }
}
