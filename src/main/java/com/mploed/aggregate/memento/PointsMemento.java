package com.mploed.aggregate.memento;


import java.util.Objects;

public class PointsMemento {
    private final int points;

    public PointsMemento(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }


    public PointsMemento plus(PointsMemento anderePunkte) {
        return new PointsMemento(points + anderePunkte.points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsMemento punkte1 = (PointsMemento) o;
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
