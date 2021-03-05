package org.example.projector.service;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.geom.Point;

public interface ProjectionCalculator {

    Point findProjection(MovingPoint carPoint);

}
