package org.example.projector.model.geom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PolarVectorTest {

    @Test
    void polarVectorWithNegativeAngle_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new PolarVector(30, -12));
    }

    @Test
    void polarVectorWithAngleValueMoreThan360_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new PolarVector(30, 400));
    }

}