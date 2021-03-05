package org.example.projector.service;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.Race;
import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;
import org.example.projector.model.geom.Vector;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

public class ProjectionCalculatorImpl implements ProjectionCalculator {

    private static final int MAX_ANGLE_DIFF = 180;
    private static final double ANGLE_IMPORTANCE_MULTIPLIER = 0.25;
    private static final int MAX_POSITION_LENGTH_ERROR = 100;

    private final List<LineSegment> segments;

    private int lastIndex;

    public ProjectionCalculatorImpl(Race race) {
        this.segments = race.getSegments();
        this.lastIndex = 0;
    }

    @Override
    public Point findProjection(MovingPoint carPoint) {
        SegmentStats bestSegmentStats = IntStream.range(lastIndex, segments.size())
                .mapToObj(index -> calculateSegmentStats(carPoint, segments.get(index), index - lastIndex))
                .max(comparingDouble(segmentStats -> calculateStatsCoefficient(segmentStats.stats)))
                .orElseThrow();
        lastIndex += bestSegmentStats.stats.index;
        return bestSegmentStats.projection;
    }

    private SegmentStats calculateSegmentStats(MovingPoint carPoint, LineSegment segment, int index) {
        Point carCoordinates = carPoint.getCoordinates();
        Point projection = calculateProjection(carCoordinates, segment);
        double squareLength = Vector.betweenPoints(projection, carCoordinates).squareLength();

        PolarVector carVector = carPoint.getVector();
        double angleDiff = calculateAngle(carVector.getAngle(), segment);

        return new SegmentStats(projection, new Stats(squareLength, angleDiff, index));
    }

    private double calculateStatsCoefficient(Stats stats) {
        // TODO: normalize coefficients
        double lengthCoefficient = calculateLengthCoefficient(stats.squareLength);
        double angleCoefficient = calculateAngleCoefficient(stats.angleDiff);
        double indexCoefficient = calculateIndexCoefficient(stats.index);
        return lengthCoefficient * angleCoefficient * indexCoefficient;
    }

    private double calculateIndexCoefficient(int index) {
        // TODO: use segment length sum starting from last point instead
        if (index > 0 && index < 3) {
            index--;
        }
        return 1. / Math.pow(1.1, index);
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
        private final int index;

        private Stats(double squareLength, double angleDiff, int index) {
            this.squareLength = squareLength;
            this.angleDiff = angleDiff;
            this.index = index;
        }
    }

}
