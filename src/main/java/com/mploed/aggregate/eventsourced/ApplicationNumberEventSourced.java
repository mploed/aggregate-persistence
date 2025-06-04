package com.mploed.aggregate.eventsourced;

import com.mploed.aggregate.memento.ApplicationNumberMemento;

import java.util.Objects;

public class ApplicationNumberEventSourced {
    private String applicationNumber;

    public ApplicationNumberEventSourced(String applicationNumber) {
        if(applicationNumber == null) {
            throw new IllegalArgumentException("ApplicationNumber must not be null");
        }
        this.applicationNumber = applicationNumber;
    }

    public String nummer() {
        return applicationNumber;
    }

    @Override
    public String toString() {
        return "ApplicationNumber{" +
                "number='" + applicationNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationNumberEventSourced that = (ApplicationNumberEventSourced) o;
        return Objects.equals(applicationNumber, that.applicationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumber);
    }
}
