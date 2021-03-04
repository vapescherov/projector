package org.example.projector.exception;

public class RaceReadException extends RuntimeException {

    public RaceReadException(Throwable cause) {
        super("Unable to read race data", cause);
    }

}
