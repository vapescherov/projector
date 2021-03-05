package org.example.projector.gui;

import org.example.projector.model.ProjectionPair;
import org.example.projector.model.Race;
import org.example.projector.model.geom.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class RaceMap extends JComponent {

    private final Race race;
    private final List<ProjectionPair> projections;
    private final int height;

    public RaceMap(Race race, List<ProjectionPair> projections, int height) {
        this.race = race;
        this.projections = projections;
        this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;

        race.getSegments().stream()
                .map(seg -> toLine(seg.getP1(), seg.getP2()))
                .forEach(g2::draw);

        g2.setColor(Color.RED);
        for (int i = 0; i < projections.size(); i++) {
            ProjectionPair projectionPair = projections.get(i);
            Point carCoordinates = projectionPair.getCarPoint().getCoordinates();
            g2.draw(toLine(carCoordinates, projectionPair.getProjection()));
            g2.drawString(String.valueOf(i + 1),
                    Double.valueOf(carCoordinates.getX() + 5).floatValue(),
                    Double.valueOf(fixY(carCoordinates.getY() + 5)).floatValue());
        }
    }

    private Line2D.Double toLine(Point p1, Point p2) {
        return new Line2D.Double(p1.getX(), fixY(p1.getY()), p2.getX(), fixY(p2.getY()));
    }

    private double fixY(double y) {
        return height - y;
    }

}
