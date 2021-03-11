package org.example.projector.model;

import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;

import java.util.Objects;

public class MovingPoint {

    private final Point coordinates;
    private final PolarVector polarVector;

    public MovingPoint(Point coordinates, PolarVector polarVector) {
        this.coordinates = coordinates;
        this.polarVector = polarVector;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public PolarVector getVector() {
        return polarVector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovingPoint that = (MovingPoint) o;
        return Objects.equals(coordinates, that.coordinates) && Objects.equals(polarVector, that.polarVector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, polarVector);
    }

    @Override
    public String toString() {
        return "MovingPoint(" + coordinates + ", " + polarVector + ")";
    }

}
