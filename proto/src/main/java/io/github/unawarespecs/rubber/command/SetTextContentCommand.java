package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

import java.awt.*;

public class SetTextContentCommand implements Command {

    private AppService appService;
    private String textContent;
    private String oldTextContent;

    public SetTextContentCommand(AppService appService, String text) {
        this.appService = appService;
        this.textContent = text;
    }

    @Override
    public void execute() {
        oldTextContent = appService.getTextContent();
        appService.setTextContent(textContent);
    }

    @Override
    public void undo() {
        appService.setTextContent(oldTextContent);
    }

    @Override
    public void redo() {
        appService.setTextContent(textContent);
    }
}
