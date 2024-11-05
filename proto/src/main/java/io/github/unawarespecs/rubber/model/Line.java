package io.github.unawarespecs.rubber.model;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.command.renderer.LineRenderer;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class Line extends Shape {
    public Line(Point start, Point end, Color color, int lineThickness){
        super(start, end, color, null, lineThickness);
        setRenderer(new LineRenderer());
    }
}
