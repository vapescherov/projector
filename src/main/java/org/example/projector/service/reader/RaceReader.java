package org.example.projector.service.reader;

import org.example.projector.model.Race;

import java.io.InputStream;

public interface RaceReader {

    Race read(InputStream is);

}
