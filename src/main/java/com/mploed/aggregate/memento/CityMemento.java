package com.mploed.aggregate.memento;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Objects;

@ValueObject
class CityMemento {
    private String city;

    private CityMemento() {}

    public CityMemento(String city) {
        this.city = city;
    }

    public String city() {
        return city;
    }

    public PointsMemento calculatePoints() {
        if ("Hamburg".equalsIgnoreCase(city)) {
            return new PointsMemento(5);
        } else if ("München".equalsIgnoreCase(city)) {
            return new PointsMemento(5);
        } else {
            return new PointsMemento(0);
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
        CityMemento cityMemento1 = (CityMemento) o;
        return Objects.equals(city, cityMemento1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city);
    }
}
