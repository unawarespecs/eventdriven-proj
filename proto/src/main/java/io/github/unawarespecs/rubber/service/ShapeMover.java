package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.model.Shape;

import java.awt.*;

public class ShapeMover  implements MovingService, ScalingService {

    public ShapeMover(){

    }
    @Override
    public void move(Graphics g, Shape shape, Point start, Point newLoc) {
        System.out.println("Before: Locx: " + String.valueOf(shape.getLocation().x) + "Locy: " + String.valueOf(shape.getLocation().y) + "Endx: " + String.valueOf(shape.getEnd().x) + "Endx: " + String.valueOf(shape.getEnd().y));
                shape.getRenderer().render(g, shape);
        int dx = newLoc.x - start.x;
        int dy= newLoc.y - start.y;
        System.out.println("dx = " + String.valueOf(dx) + " dy = "  + String.valueOf(dy));
        Point newStart = new Point(shape.getLocation().x+dx, shape.getLocation().y+dy);
        Point newEnd = new Point(shape.getEnd().x+dx, shape.getEnd().y+dy);

        shape.setLocation(newStart);
        shape.setEnd(newEnd);
        System.out.println("After: Locx: " + String.valueOf(shape.getLocation().x) + "Locy: " + String.valueOf(shape.getLocation().y) + "Endx: " + String.valueOf(shape.getEnd().x) + "Endx: " + String.valueOf(shape.getEnd().y));
        shape.getRenderer().render(g, shape);
    }

    @Override
    public void scale(Graphics g, Shape shape, Point newLoc) {
        shape.getRenderer().render(g, shape);
        shape.setEnd(newLoc);
        shape.getRenderer().render(g, shape);
    }
}
