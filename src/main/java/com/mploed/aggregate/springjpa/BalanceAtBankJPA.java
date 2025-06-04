package com.mploed.aggregate.springjpa;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

@ValueObject
@Embeddable
class BalanceAtBankJPA {
    private BigDecimal balance;

    private BalanceAtBankJPA() {
    }

    public BalanceAtBankJPA(BigDecimal balance) {
        this.balance = balance;
    }

    public BalanceAtBankJPA(int balance) {
        this.balance = new BigDecimal(balance);
    }

    public PointsJPA calculatePoints() {
        if (balance.compareTo(new BigDecimal(10000))==1) {
            return new PointsJPA(5);
        } else {
            return new PointsJPA(0);
        }
    }

    @Override
    public String toString() {
        return "Balance{" +
                "balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceAtBankJPA balanceAtBankJPA1 = (BalanceAtBankJPA) o;
        return Objects.equals(balance, balanceAtBankJPA1.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(balance);
    }
}
