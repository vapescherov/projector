package org.example.projector.service;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.Vector;

import java.util.List;

import static java.util.Comparator.comparingDouble;

public class ProjectionCalculatorImpl implements ProjectionCalculator {

    @Override
    public Point findProjection(List<LineSegment> segments, MovingPoint carPoint) {
        Point carCoordinates = carPoint.getCoordinates();
        return segments.stream()
                .map(segment -> calculateProjection(carCoordinates, segment))
                .min(comparingDouble(projectedPoint -> Vector.betweenPoints(projectedPoint, carCoordinates).squareLength()))
                .orElseThrow();
    }

    private static Point calculateProjection(Point point, LineSegment segment) {
        Point p1 = segment.getP1();
        Point p2 = segment.getP2();

        Vector v1 = Vector.betweenPoints(p1, point);
        Vector v2 = Vector.betweenPoints(p1, p2);

        Vector projection = v1.projectOnto(v2);
        return p1.add(projection);
    }

}
