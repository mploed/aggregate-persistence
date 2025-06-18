package com.mploed.aggregate.jmolecules;

import java.math.BigDecimal;

import org.jmolecules.ddd.types.ValueObject;

record BalanceAtBank(BigDecimal balance) implements ValueObject {

	public BalanceAtBank(int balance) {
		this(new BigDecimal(balance));
	}

	public Points calculatePoints() {
		return balance.compareTo(new BigDecimal(10000)) == 1 ? new Points(5) : new Points(0);
	}
}
