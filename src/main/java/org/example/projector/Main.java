package org.example.projector;

import org.example.projector.gui.RaceMap;
import org.example.projector.model.MovingPoint;
import org.example.projector.model.ProjectionPair;
import org.example.projector.model.Race;
import org.example.projector.service.*;
import org.example.projector.service.reader.CarLocationReader;
import org.example.projector.service.reader.CarLocationReaderImpl;
import org.example.projector.service.reader.RaceReader;
import org.example.projector.service.reader.RaceReaderImpl;
import org.example.projector.utils.ArgumentParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArgumentParser argumentParser = new ArgumentParser(args);

        Race race = raceFromFile(argumentParser.getAsString("-r", "--race"));
        CarLocationProvider carLocationProvider = carLocationProviderFromFile(argumentParser.getAsString("-l", "--locations"));
        ProjectionCalculator projectionCalculator = new ProjectionCalculatorImpl(race);

        ProjectionService projectionService = new ProjectionServiceImpl(carLocationProvider, projectionCalculator);

        Stream<ProjectionPair> projections = projectionService.getProjections();

        List<ProjectionPair> projectionsList = projections.collect(toList());

        boolean showGui = argumentParser.hasAnyFlag("--gui");
        if (showGui) {
            EventQueue.invokeLater(() -> showGui(race, projectionsList));
        }
    }

    private static void showGui(Race race, List<ProjectionPair> projectionsList) {
        JFrame frame = new JFrame();
        int height = 1000;
        frame.setSize(1000, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(new RaceMap(race, projectionsList, height));
    }

    private static Race raceFromFile(String filename) throws FileNotFoundException {
        RaceReader raceReader = new RaceReaderImpl();
        return raceReader.read(new FileInputStream(filename));
    }

    private static CarLocationProvider carLocationProviderFromFile(String filename) throws FileNotFoundException {
        CarLocationReader locationReader = new CarLocationReaderImpl();
        List<MovingPoint> movingPoints = locationReader.read(new FileInputStream(filename));
        return new ListCarLocationProvider(movingPoints);
    }

}
