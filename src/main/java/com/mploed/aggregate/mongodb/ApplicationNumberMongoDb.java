package com.mploed.aggregate.mongodb;


import java.util.Objects;

public class ApplicationNumberMongoDb {
    private String applicationNumber;

    public ApplicationNumberMongoDb(String applicationNumber) {
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
        ApplicationNumberMongoDb that = (ApplicationNumberMongoDb) o;
        return Objects.equals(applicationNumber, that.applicationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationNumber);
    }
}
