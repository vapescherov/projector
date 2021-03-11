package org.example.projector.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgumentParserTest {

    @Test
    void hasAnyFlag_shouldReturnTrueIfOneFlagExists() {
        ArgumentParser argumentParser = new ArgumentParser(new String[]{"--gui", "--race"});

        boolean showGui = argumentParser.hasAnyFlag("--gui");

        assertTrue(showGui);
    }

    @Test
    void hasAnyFlag_shouldReturnFalseIfSpecifiedFlagNotFound() {
        ArgumentParser argumentParser = new ArgumentParser(new String[]{"--race", "--locations"});

        boolean showGui = argumentParser.hasAnyFlag("--gui");

        assertFalse(showGui);
    }

    @Test
    void hasAnyFlag_shouldReturnTrueIfMultipleFlagsFound() {
        ArgumentParser argumentParser = new ArgumentParser(new String[]{"-g", "--gui"});

        boolean showGui = argumentParser.hasAnyFlag("--gui", "-g");

        assertTrue(showGui);
    }

}