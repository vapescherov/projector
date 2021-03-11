package org.example.projector.model.geom;

public class PolarVector {

    private final double length;
    private final double angle;

    public PolarVector(double length, double angle) {
        if (angle < 0 || angle > 360) {
            throw new IllegalArgumentException(
                    String.format("Invalid angle: %,.2f, expected value should be between [0 .. 360] ", angle));
        }
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
