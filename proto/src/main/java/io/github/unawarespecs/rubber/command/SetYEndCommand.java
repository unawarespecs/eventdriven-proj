package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.appfx.model.Shape;


import java.awt.*;

public class SetYEndCommand implements Command {
    Shape shape;
    Point newEnd;
    Point prevEnd;

    public SetYEndCommand(AppService appService, int y){
        Shape shape = appService.getSelectedShape();
        prevEnd = shape.getEnd();
        newEnd = prevEnd;
        newEnd.y = y;
    }
    @Override
    public void execute() {
        shape.setLocation(newEnd);
    }

    @Override
    public void undo() {
        shape.setLocation(prevEnd);
    }

    @Override
    public void redo() {
        shape.setLocation(prevEnd);
    }
}
