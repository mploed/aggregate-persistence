package com.mploed.aggregate.eventsourced;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Objects;

@ValueObject
class CityEventSourced {
    private String city;

    private CityEventSourced() {}

    public CityEventSourced(String city) {
        this.city = city;
    }

    public String city() {
        return city;
    }

    public PointsEventSourced calculatePoints() {
        if ("Hamburg".equalsIgnoreCase(city)) {
            return new PointsEventSourced(5);
        } else if ("München".equalsIgnoreCase(city)) {
            return new PointsEventSourced(5);
        } else {
            return new PointsEventSourced(0);
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
        CityEventSourced cityEventSourced1 = (CityEventSourced) o;
        return Objects.equals(city, cityEventSourced1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city);
    }
}
