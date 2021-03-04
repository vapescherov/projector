package org.example.projector.service.reader;

import org.example.projector.exception.CarLocationReadException;
import org.example.projector.model.MovingPoint;
import org.example.projector.model.geom.Point;
import org.example.projector.model.geom.PolarVector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CarLocationReaderImpl implements CarLocationReader {

    @Override
    public List<MovingPoint> read(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            List<MovingPoint> points = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] split = line.split(" ");
                    double x = Double.parseDouble(split[0]);
                    double y = Double.parseDouble(split[1]);
                    double length = Double.parseDouble(split[2]);
                    double angle = Double.parseDouble(split[3]);
                    points.add(new MovingPoint(new Point(x, y), new PolarVector(length, angle)));
                }
            }
            return points;
        } catch (Exception e) {
            throw new CarLocationReadException(e);
        }
    }

}
