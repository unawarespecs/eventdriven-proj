package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

import java.awt.*;


public class SetShapeFillColorCommand implements Command {
    Shape shape;
    Color fillColor;
    Color prevColor;
    public SetShapeFillColorCommand(AppService appservice, Color fillColor){
        shape = appservice.getSelectedShape();
        prevColor = shape.getColor();
        this.fillColor = fillColor;
    }

    @Override
    public void execute() {
        shape.setFill(fillColor);
    }
    @Override
    public void undo() {
        shape.setFill(prevColor);
    }
    @Override
    public void redo() {
        shape.setFill(fillColor);
    }
}


