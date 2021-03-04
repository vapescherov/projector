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

    public RaceMap(Race race, List<ProjectionPair> projections) {
        this.race = race;
        this.projections = projections;
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;

        race.getSegments().stream()
                .map(seg -> toLine(seg.getP1(), seg.getP2()))
                .forEach(g2::draw);

        g.setColor(Color.RED);

        projections.stream()
                .map(projectionPair -> toLine(projectionPair.getCarPoint().getCoordinates(), projectionPair.getProjection()))
                .forEach(g2::draw);
    }

    private static Line2D.Double toLine(Point p1, Point p2) {
        return new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

}
