package com.mploed.aggregate.springjpa;


import java.util.Objects;

public class PointsJPA {
    private final int points;

    public PointsJPA(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }


    public PointsJPA plus(PointsJPA anderePunkte) {
        return new PointsJPA(points + anderePunkte.points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsJPA punkte1 = (PointsJPA) o;
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
