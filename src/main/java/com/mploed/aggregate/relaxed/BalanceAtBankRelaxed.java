package com.mploed.aggregate.relaxed;

import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

@ValueObject
public class BalanceAtBankRelaxed {
    private BigDecimal balance;


    private BalanceAtBankRelaxed() {
    }

    public BalanceAtBankRelaxed(BigDecimal balance) {
        this.balance = balance;
    }

    public BalanceAtBankRelaxed(int balance) {
        this.balance = new BigDecimal(balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public PointsRelaxed calculatePoints() {
        if (balance.compareTo(new BigDecimal(10000))==1) {
            return new PointsRelaxed(5);
        } else {
            return new PointsRelaxed(0);
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
        BalanceAtBankRelaxed balanceAtBankRelaxed1 = (BalanceAtBankRelaxed) o;
        return Objects.equals(balance, balanceAtBankRelaxed1.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(balance);
    }
}
