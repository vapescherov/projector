package org.example.projector.service;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}