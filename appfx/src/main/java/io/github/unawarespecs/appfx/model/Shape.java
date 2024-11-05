package io.github.unawarespecs.appfx.model;

import io.github.unawarespecs.appfx.service.Renderer;
import lombok.Data;

import java.awt.*;

@Data
public class Shape implements Cloneable {
    private int id;
    private Point location;
    private Point end;
    private Color color;
    private Color fill = null;
    private int lineThickness;
    private Renderer renderer;
    private boolean selected;
    private boolean visible;
    public Shape(Point location, Point end, Color color, Color fill, int lineThickness) {
        this.location = location;
        this.end = end;
        this.color = color;
        this.fill = fill;
        this.lineThickness = lineThickness;
    }

    @Override
    public Shape clone() {
        try {
            Shape clone = (Shape) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
