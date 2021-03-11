package org.example.projector.service;

import org.example.projector.model.MovingPoint;

import java.util.List;
import java.util.stream.Stream;

public class ListCarLocationProvider implements CarLocationProvider {

    private final List<MovingPoint> points;

    public ListCarLocationProvider(List<MovingPoint> points) {
        this.points = points;
    }

    /**
     * @return stream simulating a moving car
     */
    @Override
    public Stream<MovingPoint> asSequentialStream() {
        return points.stream().sequential();
    }

}
