package org.example.projector.service;

import org.example.projector.model.MovingPoint;

import java.util.stream.Stream;

public interface CarLocationProvider {

    Stream<MovingPoint> asSequentialStream();

}
