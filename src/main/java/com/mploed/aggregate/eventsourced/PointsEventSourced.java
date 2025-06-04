package com.mploed.aggregate.eventsourced;


import java.util.Objects;

public class PointsEventSourced {
    private final int points;

    public PointsEventSourced(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }


    public PointsEventSourced plus(PointsEventSourced anderePunkte) {
        return new PointsEventSourced(points + anderePunkte.points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsEventSourced punkte1 = (PointsEventSourced) o;
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
