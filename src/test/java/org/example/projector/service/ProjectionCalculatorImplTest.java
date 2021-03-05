package org.example.projector.service;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectionCalculatorImplTest {

    @Test
    void projectSinglePointToSingleSegmentOrthogonally() {
        List<LineSegment> segments = List.of(new LineSegment(new Point(0, 0), new Point(2, 0)));
        MovingPoint car = new MovingPoint(new Point(1, 1), new PolarVector(2, 0));

        Point projection = new ProjectionCalculatorImpl().findProjection(segments, car);

        assertEquals(projection, new Point(1, 0));
    }

    @Test
    void projectSinglePointToSingleSegmentToLeftClosestPoint() {
        Point startOfSegment = new Point(1, 0);
        List<LineSegment> segments = List.of(new LineSegment(startOfSegment, new Point(2, 0)));
        MovingPoint car = new MovingPoint(new Point(0, 1), new PolarVector(2, 0));

        Point projection = new ProjectionCalculatorImpl().findProjection(segments, car);

        assertEquals(projection, startOfSegment);
    }

    @Test
    void projectSinglePointToSingleSegmentToRightClosestPoint() {
        Point endOfSegment = new Point(2, 0);
        List<LineSegment> segments = List.of(new LineSegment(new Point(1, 0), endOfSegment));
        MovingPoint car = new MovingPoint(new Point(3, 1), new PolarVector(2, 0));

        Point projection = new ProjectionCalculatorImpl().findProjection(segments, car);

        assertEquals(projection, endOfSegment);
    }

    @Test
    void projectPointToSegmentWithBestAngle() {
        LineSegment segment = new LineSegment(new Point(768.5, 747), new Point(514.5, 883.5));
        List<LineSegment> segments = List.of(
                new LineSegment(new Point(386, 936.5), new Point(590, 926.5)),
                new LineSegment(new Point(590, 926.5), new Point(840, 809.5)),
                new LineSegment(new Point(840, 809.5), new Point(768.5, 747)),
                segment
        );

        MovingPoint car = new MovingPoint(new Point(600.5, 880.5), new PolarVector(90.0, 206.7));

        Point projection = new ProjectionCalculatorImpl().findProjection(segments, car);

        assertTrue(onLine(projection, segment));
    }

    private static boolean onLine(Point point, LineSegment segment) {
        double threshold = .01;
        return distance(segment.getP1(), point) + distance(point, segment.getP2()) - distance(segment.getP1(), segment.getP2()) < threshold;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

}