package com.mploed.aggregate.mongodb;


import java.util.Objects;

public class PointsMongoDb {
    private final int points;

    public PointsMongoDb(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }


    public PointsMongoDb plus(PointsMongoDb anderePunkte) {
        return new PointsMongoDb(points + anderePunkte.points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsMongoDb punkte1 = (PointsMongoDb) o;
        return points == punkte1.points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "Punkte{" +
                "punkte=" + points +
                '}';
    }
}
