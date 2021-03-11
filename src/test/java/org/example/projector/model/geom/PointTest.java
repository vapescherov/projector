package org.example.projector.model.geom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void addTwoCoordinates_shouldCreateNewPointWithAddedCoordinates() {
        Point point = new Point(2, 3);

        Point result = point.add(4, 5);

        assertEquals(6, result.getX(), 0.001);
        assertEquals(8, result.getY(), 0.001);
    }

    @Test
    void addVector_shouldCreateNewPointWithCoordinatesIncreasedByVectorCoordinates() {
        Point point = new Point(2, 3);
        Vector vector = new Vector(5, 6);

        Point result = point.add(vector);

        assertEquals(7, result.getX(), 0.001);
        assertEquals(9, result.getY(), 0.001);
    }

}