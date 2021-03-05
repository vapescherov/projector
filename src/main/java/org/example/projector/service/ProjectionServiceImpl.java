package org.example.projector.service;

import org.example.projector.model.ProjectionPair;

import java.util.stream.Stream;

public class ProjectionServiceImpl implements ProjectionService {

    private final CarLocationProvider carLocationProvider;
    private final ProjectionCalculator projectionCalculator;

    public ProjectionServiceImpl(CarLocationProvider carLocationProvider, ProjectionCalculator projectionCalculator) {
        this.carLocationProvider = carLocationProvider;
        this.projectionCalculator = projectionCalculator;
    }

    @Override
    public Stream<ProjectionPair> getProjections() {
        return carLocationProvider.asSequentialStream()
                .map(location -> new ProjectionPair(location, projectionCalculator.findProjection(location)));
    }

}
