package com.mploed.aggregate.relaxed;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.math.BigDecimal;
import java.util.Objects;


@AggregateRoot
public class ApplicantScoringClusterRelaxed {
    @Identity
    private final ApplicationNumberRelaxed applicationNumberRelaxed;

    private CityRelaxed city;
    private BalanceAtBankRelaxed balance;


    public ApplicantScoringClusterRelaxed(ApplicationNumberRelaxed applicationNumberRelaxed) {
        if(applicationNumberRelaxed == null) {
            throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
        }
        this.applicationNumberRelaxed = applicationNumberRelaxed;
        this.balance = new BalanceAtBankRelaxed(0);
    }

    public PointsRelaxed score() {
        if(city == null) {
            return null;
        }
        if (balance == null) {
            return null;        }

        PointsRelaxed result = new PointsRelaxed(0);
        result = result.plus(city.calculatePoints());
        result = result.plus(balance.calculatePoints());

        return result;
    }

    public ApplicationNumberRelaxed applicationNumber() {
        return applicationNumberRelaxed;
    }

    public void addCity(String wohnort) {
        this.city = new CityRelaxed(wohnort);
    }

    public void addBalance(BigDecimal balance) {
        this.balance = new BalanceAtBankRelaxed(balance);
    }

    public CityRelaxed getCity() {
        return city;
    }

    public BalanceAtBankRelaxed getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantScoringClusterRelaxed that = (ApplicantScoringClusterRelaxed) o;
        return Objects.equals(applicationNumberRelaxed, that.applicationNumberRelaxed);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumberRelaxed);
    }
}
