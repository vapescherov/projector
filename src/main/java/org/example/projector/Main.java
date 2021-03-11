package org.example.projector;

import org.example.projector.gui.RaceMap;
import org.example.projector.model.MovingPoint;
import org.example.projector.model.ProjectionPair;
import org.example.projector.model.Race;
import org.example.projector.service.*;
import org.example.projector.service.reader.InputStreamCarLocationReader;
import org.example.projector.service.reader.InputStreamRaceReader;
import org.example.projector.service.writer.OutputStreamProjectionResultWriter;
import org.example.projector.utils.ArgumentParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ArgumentParser argumentParser = new ArgumentParser(args);

        String racePath = argumentParser.getAsString("-r", "--race");
        String locationsPath = argumentParser.getAsString("-l", "--locations");
        boolean showGui = argumentParser.hasAnyFlag("--gui");

        Race race = raceFromFile(racePath);

        ProjectionService projectionService = new ProjectionServiceImpl(
                carLocationProviderFromFile(locationsPath), new ProjectionCalculatorImpl(race));

        List<ProjectionPair> projections = projectionService.getProjections();
        printProjections(projections);

        if (showGui) {
            EventQueue.invokeLater(() -> showGui(race, projections));
        }
    }

    private static void showGui(Race race, List<ProjectionPair> projections) {
        JFrame frame = new JFrame();
        int height = 1000;
        frame.setSize(1000, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(new RaceMap(race, projections, height));
    }

    private static Race raceFromFile(String filename) throws FileNotFoundException {
        return new InputStreamRaceReader(new FileInputStream(filename)).read();
    }

    private static CarLocationProvider carLocationProviderFromFile(String filename) throws FileNotFoundException {
        List<MovingPoint> movingPoints = new InputStreamCarLocationReader(new FileInputStream(filename)).read();
        return new ListCarLocationProvider(movingPoints);
    }

    private static void printProjections(List<ProjectionPair> projections) {
        new OutputStreamProjectionResultWriter(System.out).write(projections);
    }

}
