package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

public class SetSelectedShapeCommand implements Command {
    private AppService appService;
    private Shape shape;
    private Shape prevShape;
    public SetSelectedShapeCommand(AppService appService, Shape shape){
        this.appService=appService;
        this.shape = shape;

    }

    public void execute(){
        this.prevShape = appService.getSelectedShape();
        appService.setSelectedShape(prevShape);
    }

    @Override
    public void undo() {
        appService.setSelectedShape(prevShape);
    }

    @Override
    public void redo() {
        appService.setSelectedShape(shape);
    }
}
