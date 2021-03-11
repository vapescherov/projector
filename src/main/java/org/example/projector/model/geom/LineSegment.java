package org.example.projector.model.geom;

import java.util.Objects;

public class LineSegment {

    private final Point p1;
    private final Point p2;

    public LineSegment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment that = (LineSegment) o;
        return Objects.equals(p1, that.p1) && Objects.equals(p2, that.p2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }

    @Override
    public String toString() {
        return "LineSegment[" + p1 + ", " + p2 + "]";
    }

}
