package org.example.projector.model;

import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;

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

}
