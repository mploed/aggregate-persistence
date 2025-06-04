package com.mploed.aggregate.springjpa;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Objects;

@Embeddable
public class ApplicationNumberJPA {
    private String applicationNumber;

    private ApplicationNumberJPA() {
    }

    public ApplicationNumberJPA(String applicationNumber) {
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
        ApplicationNumberJPA that = (ApplicationNumberJPA) o;
        return Objects.equals(applicationNumber, that.applicationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumber);
    }
}
