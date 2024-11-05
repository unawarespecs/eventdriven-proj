package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.appfx.model.Shape;


import java.awt.*;

public class SetXLocationCommand implements Command {
    Shape shape;
    Point newLoc;
    Point prevLoc;

    public SetXLocationCommand(AppService appService, int x){
        Shape shape = appService.getSelectedShape();
        prevLoc = shape.getLocation();
        newLoc = prevLoc;
        newLoc.x = x;
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
        shape.setLocation(prevLoc);
    }
}
