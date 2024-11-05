package io.github.unawarespecs.rubber.command;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.appfx.model.Shape;

import java.awt.*;

public class SetShapeColorCommand implements Command {
    Shape shape;
    Color color;
    Color prevColor;
    public SetShapeColorCommand(AppService appservice, Color color){
         this.shape = appservice.getSelectedShape();
         this.prevColor = shape.getColor();
         this.color = color;
    }

    @Override
    public void execute() {
        shape.setColor(color);
    }

    @Override
    public void undo() {
        shape.setColor(prevColor);
    }

    @Override
    public void redo() {
        shape.setColor(color);
    }
}


