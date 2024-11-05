package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.appfx.model.Shape;


import java.awt.Point;
public class SetYLocationCommand implements Command {
    Shape shape;
    Point newLoc;
    Point prevLoc;

    public SetYLocationCommand(AppService appService, int y){
        Shape shape = appService.getSelectedShape();
        prevLoc = shape.getLocation();
        newLoc = prevLoc;
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
        shape.setLocation(prevLoc);
    }
}
