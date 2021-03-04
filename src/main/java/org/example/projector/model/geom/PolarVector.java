package org.example.projector.model.geom;

public class PolarVector {

    private final double length;
    private final double angle;

    public PolarVector(double length, double angle) {
        this.length = length;
        this.angle = angle;
    }

    public double getLength() {
        return length;
    }

    public double getAngle() {
        return angle;
    }

}
