package org.example.projector.service.reader;

import org.example.projector.model.MovingPoint;

import java.io.InputStream;
import java.util.List;

public interface CarLocationReader {

    List<MovingPoint> read(InputStream is);

}
