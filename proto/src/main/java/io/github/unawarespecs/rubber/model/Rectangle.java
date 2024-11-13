package io.github.unawarespecs.rubber.model;
import java.awt.*;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.command.renderer.RectangleRenderer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rectangle  extends Shape {
    public Rectangle(Point start, Point end, Color color, Color fill, Color gFillOne, Color gFillTwo, int lineThickness){
        super(start, end, color, fill, gFillOne, gFillTwo, lineThickness);

        setRenderer(new RectangleRenderer());
    }
}
