package io.github.unawarespecs.rubber.model;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.command.renderer.EllipseRenderer;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class Ellipse extends Shape {
    public Ellipse(Point start, Point end, Color color, Color fill, Color gFillOne, Color gFillTwo, int lineThickness){
        super(start, end, color, fill, gFillOne, gFillTwo, lineThickness);

        setRenderer(new EllipseRenderer());
    }
}
