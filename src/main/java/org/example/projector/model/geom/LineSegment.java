package org.example.projector.model.geom;

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

}
