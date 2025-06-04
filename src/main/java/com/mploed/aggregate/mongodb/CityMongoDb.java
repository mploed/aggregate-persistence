package com.mploed.aggregate.mongodb;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Objects;

@ValueObject
class CityMongoDb {
    private String city;

    private CityMongoDb() {}

    public CityMongoDb(String city) {
        this.city = city;
    }

    public PointsMongoDb calculatePoints() {
        if ("Hamburg".equalsIgnoreCase(city)) {
            return new PointsMongoDb(5);
        } else if ("München".equalsIgnoreCase(city)) {
            return new PointsMongoDb(5);
        } else {
            return new PointsMongoDb(0);
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
        CityMongoDb cityMongoDb1 = (CityMongoDb) o;
        return Objects.equals(city, cityMongoDb1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city);
    }
}
