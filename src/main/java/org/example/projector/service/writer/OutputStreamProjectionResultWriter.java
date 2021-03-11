package org.example.projector.service.writer;

import org.example.projector.model.MovingPoint;
import org.example.projector.model.ProjectionPair;
import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class OutputStreamProjectionResultWriter implements ProjectionResultWriter {

    private static final String FORMAT = "(%,.2f, %,.2f)";

    private final OutputStream outputStream;

    public OutputStreamProjectionResultWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(List<ProjectionPair> projectionPairs) {
        try (PrintStream printStream = new PrintStream(outputStream, true)) {
            projectionPairs.forEach(pair -> {
                MovingPoint carPoint = pair.getCarPoint();

                String coordinates = formatCarCoordinates(carPoint.getCoordinates());
                String vectorFormat = formatVector(carPoint.getVector());
                String projectionFormat = formatProjection(pair.getProjection());

                printStream.printf("[%s %s] -> %s%n", coordinates, vectorFormat, projectionFormat);
            });
        }
    }

    private String formatCarCoordinates(Point carCoordinates) {
        return String.format(FORMAT, carCoordinates.getX(), carCoordinates.getY());
    }

    private String formatVector(PolarVector vector) {
        return String.format("(%,.2f, %,.2f°)", vector.getLength(), vector.getAngle());
    }

    private String formatProjection(Point projection) {
        return String.format(FORMAT, projection.getX(), projection.getY());
    }

}
