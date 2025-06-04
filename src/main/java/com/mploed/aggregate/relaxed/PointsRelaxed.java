package com.mploed.aggregate.relaxed;


import java.util.Objects;

public class PointsRelaxed {
    private final int points;

    public PointsRelaxed(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }


    public PointsRelaxed plus(PointsRelaxed anderePunkte) {
        return new PointsRelaxed(points + anderePunkte.points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsRelaxed punkte1 = (PointsRelaxed) o;
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
