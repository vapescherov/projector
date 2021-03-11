package org.example.projector.model.geom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorTest {

    @Test
    void vectorBetweenPoints_shouldReturnNewVectorWithCoordinatesEqualToDifferenceBetweenPoints() {
        Vector vector = Vector.betweenPoints(new Point(5, 10), new Point(10, 20));

        assertEquals(5, vector.getX(), 0.001);
        assertEquals(10, vector.getY(), 0.001);
    }

    @Test
    void angleOfDiagonalVector_shouldReturn45() {
        Vector vector = new Vector(5, 5);

        double angle = vector.getAngle();

        assertEquals(45, angle, 0.001);
    }

    @Test
    void angleOfRightHorizontalVector_shouldReturn0() {
        Vector vector = new Vector(5, 0);

        double angle = vector.getAngle();

        assertEquals(0, angle, 0.001);
    }

    @Test
    void angleOfHighVerticalVector_shouldReturn90() {
        Vector vector = new Vector(0, 5);

        double angle = vector.getAngle();

        assertEquals(90, angle, 0.001);
    }

    @Test
    void angleOfLeftHorizontalVector_shouldReturn180() {
        Vector vector = new Vector(-5, 0);

        double angle = vector.getAngle();

        assertEquals(180, angle, 0.001);
    }

    @Test
    void angleOfLowVerticalVector_shouldReturn270() {
        Vector vector = new Vector(0, -5);

        double angle = vector.getAngle();

        assertEquals(270, angle, 0.001);
    }

    @Test
    void scale_shouldIncreaseVectorByScaleFactor() {
        Vector vector = new Vector(5, 10);

        Vector scaled = vector.scale(2);

        assertEquals(10, scaled.getX(), 0.001);
        assertEquals(20, scaled.getY(), 0.001);
    }

    @Test
    void dotProductBetweenOrthogonalVectors_shouldBe0() {
        Vector vector = new Vector(5, 0);

        double dotProduct = vector.dotProduct(new Vector(0, 10));

        assertEquals(0, dotProduct, 0.001);
    }

    @Test
    void dotProductOfSimilarVector_shouldReturnSquareLength() {
        Vector vector = new Vector(5, 5);

        double dotProduct = vector.dotProduct(vector);

        assertEquals(50, dotProduct, 0.001);
    }

    @Test
    void projectOntoHorizontalVector_shouldReturnXCoordAnd0() {
        Vector vector = new Vector(5, 5);

        Vector projected = vector.projectOnto(new Vector(10, 0));

        assertEquals(5, projected.getX(), 0.001);
        assertEquals(0, projected.getY(), 0.001);
    }

    @Test
    void projectOntoVerticalVector_shouldReturn0AndYCoord() {
        Vector vector = new Vector(5, 5);

        Vector projected = vector.projectOnto(new Vector(0, 10));

        assertEquals(0, projected.getX(), 0.001);
        assertEquals(5, projected.getY(), 0.001);
    }

    @Test
    void projectOntoLongDiagonalVector_shouldReturnDiagonalVector() {
        Vector vector = new Vector(6, 0);

        Vector projected = vector.projectOnto(new Vector(10, 10));

        assertEquals(3, projected.getX(), 0.001);
        assertEquals(3, projected.getY(), 0.001);
    }

    @Test
    void projectOntoVectorWithAngleMoreThan90_shouldReturnZeroVector() {
        Vector vector = new Vector(5, 5);

        Vector projected = vector.projectOnto(new Vector(-10, 0));

        assertEquals(0, projected.getX(), 0.001);
        assertEquals(0, projected.getY(), 0.001);
    }

}