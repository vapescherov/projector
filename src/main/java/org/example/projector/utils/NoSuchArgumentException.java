package org.example.projector.utils;

public class NoSuchArgumentException extends RuntimeException {

    public NoSuchArgumentException(String... flags) {
        super(String.format("No argument with flags '%s' provided", String.join(", ", flags)));
    }

}
