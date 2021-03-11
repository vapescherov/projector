package org.example.projector.service;

import org.example.projector.model.ProjectionPair;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProjectionServiceImpl implements ProjectionService {

    private final CarLocationProvider carLocationProvider;
    private final ProjectionCalculator projectionCalculator;

    public ProjectionServiceImpl(CarLocationProvider carLocationProvider, ProjectionCalculator projectionCalculator) {
        this.carLocationProvider = carLocationProvider;
        this.projectionCalculator = projectionCalculator;
    }

    @Override
    public List<ProjectionPair> getProjections() {
        return carLocationProvider.asSequentialStream()
                .map(location -> new ProjectionPair(location, projectionCalculator.findProjection(location)))
                .collect(toList());
    }

}
