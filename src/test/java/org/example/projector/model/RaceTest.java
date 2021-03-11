package org.example.projector.model;

import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceTest {

    @Test
    void raceShouldBuildSegmentsFromPoints() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5, 5);
        Point p3 = new Point(10, 6);
        Point p4 = new Point(7, 10);
        Point p5 = new Point(0, 10);
        Race race = new Race(List.of(p1, p2, p3, p4, p5));

        List<LineSegment> segments = race.getSegments();

        List<LineSegment> expected = List.of(
                new LineSegment(p1, p2),
                new LineSegment(p2, p3),
                new LineSegment(p3, p4),
                new LineSegment(p4, p5),
                new LineSegment(p5, p1)
        );
        assertEquals(expected, segments);
    }

}