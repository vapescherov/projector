package org.example.projector.model;

import org.example.projector.model.geom.Point;

public class ProjectionPair {

    private final MovingPoint carPoint;
    private final Point projection;

    public ProjectionPair(MovingPoint carPoint, Point projection) {
        this.carPoint = carPoint;
        this.projection = projection;
    }

    public MovingPoint getCarPoint() {
        return carPoint;
    }

    public Point getProjection() {
        return projection;
    }

    @Override
    public String toString() {
        return "ProjectionPair{" +
                "carPoint=" + carPoint +
                ", projection=" + projection +
                '}';
    }

}
