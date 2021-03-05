package org.example.projector.service;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;
import org.example.projector.model.geom.Vector;

import java.util.List;

import static java.util.Comparator.comparingDouble;

public class ProjectionCalculatorImpl implements ProjectionCalculator {

    private static final int MAX_ANGLE_DIFF = 180;
    private static final double ANGLE_IMPORTANCE_MULTIPLIER = 0.25;
    private static final int MAX_POSITION_LENGTH_ERROR = 100;

    @Override
    public Point findProjection(List<LineSegment> segments, MovingPoint carPoint) {
        return segments.stream()
                .map(segment -> calculateSegmentStats(carPoint, segment))
                .max(comparingDouble(segmentStats -> calculateStatsCoefficient(segmentStats.stats)))
                .map(segmentStats -> segmentStats.projection)
                .orElseThrow();
    }

    private SegmentStats calculateSegmentStats(MovingPoint carPoint, LineSegment segment) {
        Point carCoordinates = carPoint.getCoordinates();
        Point projection = calculateProjection(carCoordinates, segment);
        double squareLength = Vector.betweenPoints(projection, carCoordinates).squareLength();

        PolarVector carVector = carPoint.getVector();
        double angleDiff = calculateAngle(carVector.getAngle(), segment);

        return new SegmentStats(projection, new Stats(squareLength, angleDiff));
    }

    private double calculateStatsCoefficient(Stats stats) {
        double lengthCoefficient = calculateLengthCoefficient(stats.squareLength);
        double angleCoefficient = calculateAngleCoefficient(stats.angleDiff);
        return lengthCoefficient * angleCoefficient;
    }

    private double calculateAngleCoefficient(double angleDiff) {
        return 1 - (angleDiff / MAX_ANGLE_DIFF) * ANGLE_IMPORTANCE_MULTIPLIER;
    }

    private double calculateLengthCoefficient(double squareLength) {
        return 1 - Math.min(squareLength / (MAX_POSITION_LENGTH_ERROR * MAX_POSITION_LENGTH_ERROR), 1);
    }

    private static double calculateAngle(double carAngle, LineSegment segment) {
        double segmentAngle = Vector.betweenPoints(segment.getP1(), segment.getP2()).getAngle();
        return 180 - Math.abs(Math.abs(carAngle - segmentAngle) - 180);
    }

    private static Point calculateProjection(Point point, LineSegment segment) {
        Point p1 = segment.getP1();
        Point p2 = segment.getP2();

        Vector v1 = Vector.betweenPoints(p1, point);
        Vector v2 = Vector.betweenPoints(p1, p2);

        Vector projection = v1.projectOnto(v2);
        return p1.add(projection);
    }

    private static class SegmentStats {
        private final Point projection;
        private final Stats stats;

        public SegmentStats(Point projection, Stats stats) {
            this.projection = projection;
            this.stats = stats;
        }
    }

    private static class Stats {
        private final double squareLength;
        private final double angleDiff;

        private Stats(double squareLength, double angleDiff) {
            this.squareLength = squareLength;
            this.angleDiff = angleDiff;
        }
    }

}
