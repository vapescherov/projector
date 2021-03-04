package org.example.projector.service;

import org.example.projector.model.ProjectionPair;

import java.util.stream.Stream;

public interface ProjectionService {

    Stream<ProjectionPair> getProjections();

}
