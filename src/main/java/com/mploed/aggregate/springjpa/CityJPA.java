package com.mploed.aggregate.springjpa;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Objects;

@ValueObject
@Embeddable
class CityJPA {
    private String city;

    private CityJPA() {}

    public CityJPA(String city) {
        this.city = city;
    }

    public PointsJPA calculatePoints() {
        if ("Hamburg".equalsIgnoreCase(city)) {
            return new PointsJPA(5);
        } else if ("München".equalsIgnoreCase(city)) {
            return new PointsJPA(5);
        } else {
            return new PointsJPA(0);
        }
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityJPA cityJPA1 = (CityJPA) o;
        return Objects.equals(city, cityJPA1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city);
    }
}
