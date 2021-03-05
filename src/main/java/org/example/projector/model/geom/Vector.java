package org.example.projector.model.geom;

public class Vector {

    private final double x;
    private final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector betweenPoints(Point start, Point end) {
        return new Vector(end.getX() - start.getX(), end.getY() - start.getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double squareLength() {
        return x * x + y * y;
    }

    public double dotProduct(Vector v) {
        return x * v.getX() + y * v.getY();
    }

    public Vector scale(double value) {
        return new Vector(this.x * value, this.y * value);
    }

    public Vector projectOnto(Vector vector) {
        double squareLength = vector.squareLength();
        double coefficient = squareLength != 0 ? dotProduct(vector) / squareLength : 0;
        coefficient = Math.max(0, Math.min(coefficient, 1));
        return vector.scale(coefficient);
    }

    public double getAngle() {
        double angle = Math.toDegrees(Math.atan2(y, x));
        return angle < 0 ? 360 + angle : angle;
    }

}
