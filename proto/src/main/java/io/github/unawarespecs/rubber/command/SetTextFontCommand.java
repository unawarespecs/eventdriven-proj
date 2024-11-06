package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

import java.awt.Font;

public class SetTextFontCommand implements Command {

    private AppService appService;
    private Font font;
    private Font oldFont;

    public SetTextFontCommand(AppService appService, Font font) {
        this.appService = appService;
        this.font = font;
    }

    @Override
    public void execute() {
        oldFont = appService.getTextFont();
        appService.setTextFont(font);
    }

    @Override
    public void undo() {
        appService.setTextFont(oldFont);
    }

    @Override
    public void redo() {
        appService.setTextFont(font);
    }
}
