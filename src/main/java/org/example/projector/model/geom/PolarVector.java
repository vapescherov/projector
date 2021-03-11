package org.example.projector.model.geom;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolarVector that = (PolarVector) o;
        return Double.compare(that.length, length) == 0 && Double.compare(that.angle, angle) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, angle);
    }

    @Override
    public String toString() {
        return "PolarVector{" +
                "length=" + length +
                ", angle=" + angle +
                '}';
    }

}
