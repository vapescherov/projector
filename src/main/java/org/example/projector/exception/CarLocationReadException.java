package org.example.projector.exception;

public class CarLocationReadException extends RuntimeException {

    public CarLocationReadException(Throwable cause) {
        super("Unable to read car location data", cause);
    }

}
