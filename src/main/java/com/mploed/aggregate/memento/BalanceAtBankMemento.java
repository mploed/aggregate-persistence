package com.mploed.aggregate.memento;

import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

@ValueObject
class BalanceAtBankMemento {
    private BigDecimal balance;

    private BalanceAtBankMemento() {
    }

    public BalanceAtBankMemento(BigDecimal balance) {
        this.balance = balance;
    }

    public BalanceAtBankMemento(int balance) {
        this.balance = new BigDecimal(balance);
    }

    public BigDecimal balance() {
        return balance;
    }
    public PointsMemento calculatePoints() {
        if (balance.compareTo(new BigDecimal(10000))==1) {
            return new PointsMemento(5);
        } else {
            return new PointsMemento(0);
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
        BalanceAtBankMemento balanceAtBankMongoDb1 = (BalanceAtBankMemento) o;
        return Objects.equals(balance, balanceAtBankMongoDb1.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(balance);
    }
}
