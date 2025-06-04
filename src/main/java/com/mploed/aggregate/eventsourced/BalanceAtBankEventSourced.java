package com.mploed.aggregate.eventsourced;

import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

@ValueObject
class BalanceAtBankEventSourced {
    private BigDecimal balance;

    private BalanceAtBankEventSourced() {
    }

    public BalanceAtBankEventSourced(BigDecimal balance) {
        this.balance = balance;
    }

    public BalanceAtBankEventSourced(int balance) {
        this.balance = new BigDecimal(balance);
    }

    public BigDecimal balance() {
        return balance;
    }
    public PointsEventSourced calculatePoints() {
        if (balance.compareTo(new BigDecimal(10000))==1) {
            return new PointsEventSourced(5);
        } else {
            return new PointsEventSourced(0);
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
        BalanceAtBankEventSourced balanceAtBankMongoDb1 = (BalanceAtBankEventSourced) o;
        return Objects.equals(balance, balanceAtBankMongoDb1.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(balance);
    }
}
