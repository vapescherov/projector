package org.example.projector.model;

import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Race {

    private final List<LineSegment> segments;

    public Race(List<Point> points) {
        this.segments = buildSegments(points);
    }

    private static List<LineSegment> buildSegments(List<Point> points) {
        int size = points.size();
        return IntStream.range(0, size)
                .mapToObj(index -> new LineSegment(points.get(index), points.get((index + 1) % size)))
                .collect(toList());
    }

    public List<LineSegment> getSegments() {
        return segments;
    }

}
