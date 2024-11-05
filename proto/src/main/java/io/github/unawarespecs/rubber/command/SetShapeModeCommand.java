package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;


public class SetShapeModeCommand  implements Command {
    AppService appService;
    ShapeMode shapeMode;
    ShapeMode prevShapeMode;
    public SetShapeModeCommand(AppService appService, ShapeMode shapeMode){
        this.appService = appService;
        this.shapeMode = shapeMode;
    }

    @Override
    public void execute() {

        prevShapeMode = appService.getShapeMode();
        appService.setShapeMode(shapeMode);
     }

    @Override
    public void undo() {
        appService.setShapeMode(prevShapeMode);
    }

    @Override
    public void redo() {
        appService.setShapeMode(shapeMode);
    }
}

