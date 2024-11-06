package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.commandfx.CommandService;
import io.github.unawarespecs.rubber.command.*;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.command.*;
import io.github.unawarespecs.rubber.model.Picture;

import java.awt.*;
import java.util.List;

public class DrawingCommandAppService implements AppService {

    private AppService appService;

    public DrawingCommandAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public Shape getSelectedShape() {
        return appService.getSelectedShape();
    }

    @Override
    public void setSelectedShape(Shape shape) {
        appService.setSelectedShape(shape);
    }

    @Override
    public ShapeMode getShapeMode() {
        return appService.getShapeMode();
    }

    @Override
    public void setShapeMode(ShapeMode shapeMode) {
        appService.setShapeMode(shapeMode);
//        Command command = new SetShapeModeCommand(appService, shapeMode );
//        CommandService.ExecuteCommand(command);
    }

    @Override
    public Color getColor() {
        return appService.getColor();
    }

    @Override
    public void setColor(Color color) {
        appService.setColor(color);
        //      Command command = new SetColorCommand(appService, color );
        //       CommandService.ExecuteCommand(command);
    }


    @Override
    public Color getFillColor() {
        return appService.getFillColor();
    }

    @Override
    public void setFillColor(Color color) {
        appService.setFillColor(color);
        //    Command command = new SetFillColorCommand(appService, color );
        //    CommandService.ExecuteCommand(command);
    }

    @Override
    public int getLineThickness() {
        return appService.getLineThickness();
    }

    @Override
    public void setLineThickness(int thickness) {
        appService.setLineThickness(thickness);
        //     Command command = new SetThicknessCommand(appService, thickness );
//        CommandService.ExecuteCommand(command);
    }

    @Override
    public void addShape(Shape shape) {
        Command command = new AddShapeCommand(appService, shape);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void updateShape(Shape shape) {
        Command command = new UpdateShapeCommand(appService, shape);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void deleteShape(Shape shape) {
        Command command = new DeleteShapeCommand(appService, shape);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public List<Shape> getShapes() {
        return appService.getShapes();
    }

    @Override
    public String getImageFileName() {
        return appService.getImageFileName();
    }

    @Override
    public void setImageFileName(String imageFileName) {
        Command command = new SetImageFileNameCommand(appService, imageFileName);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public String getTextContent() {
        return appService.getTextContent();
    }

    @Override
    public void setTextContent(String text) {
        Command command = new SetTextContentCommand(appService, text);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public Font getTextFont() {
        return appService.getTextFont();
    }

    @Override
    public void setTextFont(Font font) {
        Command command = new SetTextFontCommand(appService, font);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public String getShapeImageFileName() {
        Picture picture = (Picture) getSelectedShape();
        return picture.getImageFilename();
    }

    @Override
    public void setShapeImageFileName(String imageFileName) {
        Command command = new SetShapeImageFileNameCommand(appService, imageFileName);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public String getFileName() {
        return appService.getFileName();
    }

    @Override
    public void setFileName(String fileName) {
        appService.setFileName(fileName);
    }

    @Override
    public void open() {
        appService.open();
    }

    @Override
    public void save() {
        appService.save();
    }

    @Override
    public void close() {
        appService.close();
    }

    @Override
    public void exit() {
        appService.exit();
    }

    @Override
    public void repaint() {
        appService.repaint();
    }

    @Override
    public void setTitle(String title) {
        appService.setTitle(title);
    }

    @Override
    public void setShapeXLocation(int x) {
        Command command = new SetShapeXLocation(appService, x);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void setShapeYLocation(int y) {
        Command command = new SetShapeYLocation(appService, y);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void setShapeXEnd(int x) {

    }

    @Override
    public void setShapeYEnd(int y) {

    }

    @Override
    public void undo() {
        appService.undo();
    }

    @Override
    public void redo() {
        appService.redo();
    }
}
