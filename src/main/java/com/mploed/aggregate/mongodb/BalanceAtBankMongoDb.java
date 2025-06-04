package com.mploed.aggregate.mongodb;

import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

@ValueObject
class BalanceAtBankMongoDb {
    private BigDecimal balance;

    private BalanceAtBankMongoDb() {
    }

    public BalanceAtBankMongoDb(BigDecimal balance) {
        this.balance = balance;
    }

    public BalanceAtBankMongoDb(int balance) {
        this.balance = new BigDecimal(balance);
    }

    public PointsMongoDb calculatePoints() {
        if (balance.compareTo(new BigDecimal(10000))==1) {
            return new PointsMongoDb(5);
        } else {
            return new PointsMongoDb(0);
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
        BalanceAtBankMongoDb balanceAtBankMongoDb1 = (BalanceAtBankMongoDb) o;
        return Objects.equals(balance, balanceAtBankMongoDb1.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(balance);
    }
}
