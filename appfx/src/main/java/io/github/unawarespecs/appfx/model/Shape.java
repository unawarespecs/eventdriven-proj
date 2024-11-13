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
    private Color fill;
    private int lineThickness;
    private Renderer renderer;
    private boolean selected;
    private boolean visible;
    private Color gradientColorOne;
    private Color gradientColorTwo;

    public Shape(Point location, Point end, Color color, Color fill, Color gFillOne, Color gFillTwo, int lineThickness) {
        this.location = location;
        this.end = end;
        this.color = color;
        this.fill = fill;
        this.lineThickness = lineThickness;
        this.gradientColorOne = gFillOne;
        this.gradientColorTwo = gFillTwo;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
