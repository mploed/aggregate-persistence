package com.mploed.aggregate.eventsourced.events;

import java.math.BigDecimal;

public class BalanceAddedEvent extends DomainEvent {
    private BigDecimal balance;

    private BalanceAddedEvent() {
        super(null, null);
    }
    public BalanceAddedEvent(String applicationNumber, Long version, BigDecimal balance) {
        super(applicationNumber, version);
        this.balance = balance;
    }

    public BigDecimal getBalance() { return balance; }
}