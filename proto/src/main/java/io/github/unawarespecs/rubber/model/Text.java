package io.github.unawarespecs.rubber.model;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.command.renderer.TextToolRenderer;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Text extends Shape {
    private String text;
    private java.awt.Font font;
    private java.awt.Font fallbackFont;
    private int fontSize;

    public Text(Point start, Point end, String text, Font font, int fontSize, Color color, int lineThickness) {
        super(start, end, color, null, null, null, lineThickness);
        this.text = text;
        this.font = font;
        this.fallbackFont = new Font("Arial", Font.PLAIN, 12);
        this.fontSize = fontSize;
        setRenderer(new TextToolRenderer());
    }
}
