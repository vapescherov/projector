package org.example.projector.service.writer;

import org.example.projector.model.ProjectionPair;

import java.util.List;

public interface ProjectionResultWriter {

    void write(List<ProjectionPair> projectionPairs);

}
