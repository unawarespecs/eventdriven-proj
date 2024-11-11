package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.enums.EditMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;


public class SetEditModeCommand implements Command {
    AppService appService;
    EditMode editMode;
    EditMode prevEditMode;
    public SetEditModeCommand(AppService appService, EditMode editMode){
        this.appService = appService;
        this.editMode = editMode;
    }

    @Override
    public void execute() {

        prevEditMode = appService.getEditMode();
        appService.setEditMode(editMode);
     }

    @Override
    public void undo() {
        appService.setEditMode(prevEditMode);
    }

    @Override
    public void redo() {
        appService.setEditMode(editMode);
    }
}

