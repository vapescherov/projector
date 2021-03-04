package org.example.projector.service.reader;

import org.example.projector.exception.RaceReadException;
import org.example.projector.model.Race;
import org.example.projector.model.geom.Point;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RaceReaderImpl implements RaceReader {

    @Override
    public Race read(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            List<Point> points = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] split = line.split(" ");
                    double x = Double.parseDouble(split[0]);
                    double y = Double.parseDouble(split[1]);
                    points.add(new Point(x, y));
                }
            }
            return new Race(points);
        } catch (Exception e) {
            throw new RaceReadException(e);
        }
    }

}
