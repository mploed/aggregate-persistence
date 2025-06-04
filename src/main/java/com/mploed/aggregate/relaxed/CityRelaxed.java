package com.mploed.aggregate.relaxed;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Objects;

@ValueObject
public class CityRelaxed {
    private String city;

    private CityRelaxed() {}

    public CityRelaxed(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public PointsRelaxed calculatePoints() {
        if ("Hamburg".equalsIgnoreCase(city)) {
            return new PointsRelaxed(5);
        } else if ("München".equalsIgnoreCase(city)) {
            return new PointsRelaxed(5);
        } else {
            return new PointsRelaxed(0);
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
        CityRelaxed cityRelaxed1 = (CityRelaxed) o;
        return Objects.equals(city, cityRelaxed1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city);
    }
}
