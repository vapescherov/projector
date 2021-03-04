package org.example.projector.service;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.geom.LineSegment;
import org.example.projector.model.geom.Point;

import java.util.List;

public interface ProjectionCalculator {

    Point findProjection(List<LineSegment> segments, MovingPoint carPoint);

}
