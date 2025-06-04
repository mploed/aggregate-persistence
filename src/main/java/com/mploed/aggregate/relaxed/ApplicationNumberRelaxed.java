package com.mploed.aggregate.relaxed;


import java.util.Objects;

public class ApplicationNumberRelaxed {
    private String applicationNumber;

    public ApplicationNumberRelaxed(String applicationNumber) {
        if(applicationNumber == null) {
            throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
        }
        this.applicationNumber = applicationNumber;
    }

    public String nummer() {
        return applicationNumber;
    }

    @Override
    public String toString() {
        return "Antragsnummer{" +
                "antragsnummer='" + applicationNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationNumberRelaxed that = (ApplicationNumberRelaxed) o;
        return Objects.equals(applicationNumber, that.applicationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumber);
    }
}
