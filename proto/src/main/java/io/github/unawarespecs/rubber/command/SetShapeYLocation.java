package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

import java.awt.*;


public class SetShapeYLocation implements Command {
    Shape shape;
    Point newLoc;
    Point prevLoc;
    public SetShapeYLocation(AppService appService, int y){
        shape =  appService.getSelectedShape();
        prevLoc = shape.getLocation();
        Point newLoc = prevLoc;
        newLoc.y = y;
    }
    @Override
    public void execute() {
        shape.setLocation(newLoc);
    }

    @Override
    public void undo() {
        shape.setLocation(prevLoc);
    }

    @Override
    public void redo() {
        shape.setLocation(newLoc);
    }
}
