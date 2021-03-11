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

    // properties below should be adjusted according to real data
    private static final double ANGLE_IMPORTANCE_MULTIPLIER = 0.25; // depends on sensor angle error rate
    private static final int MAX_POSITION_DISTANCE_ERROR = 100; // depends on sensor max distance error
    private static final int SEGMENT_NUMBER_THRESHOLD = 3; // should be a bit more than average number of segments between two measures, probably could be calculated on the flight
    private static final double FAR_SEGMENT_INDEX_COEFFICIENT = 0.8;

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
                // takeWhile length between point and segment increasing and more than threshold
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
        double lengthCoefficient = calculateLengthCoefficient(stats.squareLength);
        double angleCoefficient = calculateAngleCoefficient(stats.angleDiff);
        double indexCoefficient = calculateIndexCoefficient(stats.index);
        return lengthCoefficient * angleCoefficient * indexCoefficient;
    }

    private double calculateIndexCoefficient(int index) {
        // TODO: try to use segment length sum starting from last point instead
        return index < SEGMENT_NUMBER_THRESHOLD ? 1 : FAR_SEGMENT_INDEX_COEFFICIENT;
    }

    private double calculateAngleCoefficient(double angleDiff) {
        return 1 - (angleDiff / MAX_ANGLE_DIFF) * ANGLE_IMPORTANCE_MULTIPLIER;
    }

    private double calculateLengthCoefficient(double squareLength) {
        return 1 - Math.min(squareLength / (MAX_POSITION_DISTANCE_ERROR * MAX_POSITION_DISTANCE_ERROR), 1);
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
