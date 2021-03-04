package org.example.projector.service;

import org.example.projector.model.ProjectionPair;
import org.example.projector.model.Race;
import org.example.projector.model.geom.LineSegment;

import java.util.List;
import java.util.stream.Stream;

public class ProjectionServiceImpl implements ProjectionService {

    private final Race race;
    private final CarLocationProvider carLocationProvider;
    private final ProjectionCalculator projectionCalculator;

    public ProjectionServiceImpl(Race race, CarLocationProvider carLocationProvider, ProjectionCalculator projectionCalculator) {
        this.race = race;
        this.carLocationProvider = carLocationProvider;
        this.projectionCalculator = projectionCalculator;
    }

    @Override
    public Stream<ProjectionPair> getProjections() {
        List<LineSegment> segments = race.getSegments();
        return carLocationProvider.asSequentialStream()
                .map(location -> new ProjectionPair(location, projectionCalculator.findProjection(segments, location)));
    }

}
