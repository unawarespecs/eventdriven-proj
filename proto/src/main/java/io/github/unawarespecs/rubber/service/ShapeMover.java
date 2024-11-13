package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.model.Shape;

import java.awt.*;

public class ShapeMover implements MovingService, ScalingService {

    public ShapeMover() {

    }

    @Override
    public void move(Graphics g, Shape shape, Point start, Point newLoc) {
        System.out.println("[Debug] Before: start(" + shape.getLocation().x + "," + shape.getLocation().y + ") end(" + shape.getEnd().x + "," + shape.getEnd().y + ")");
        shape.getRenderer().render(g, shape);
        int dx = newLoc.x - start.x;
        int dy = newLoc.y - start.y;
        System.out.println("[Debug] dx = " + dx + " dy = " + dy);
        Point newStart = new Point(shape.getLocation().x + dx, shape.getLocation().y + dy);
        Point newEnd = new Point(shape.getEnd().x + dx, shape.getEnd().y + dy);

        shape.setLocation(newStart);
        shape.setEnd(newEnd);
        System.out.println("[Debug] After: start(" + shape.getLocation().x + "," + shape.getLocation().y + ") end(" + shape.getEnd().x + "," + shape.getEnd().y + ")");
        shape.getRenderer().render(g, shape);
    }

    @Override
    public void scale(Graphics g, Shape shape, Point newLoc) {
        shape.getRenderer().render(g, shape);
        shape.setEnd(newLoc);
        shape.getRenderer().render(g, shape);
    }
}
