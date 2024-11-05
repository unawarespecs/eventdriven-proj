package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.CommandService;
import io.github.unawarespecs.rubber.component.*;
import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.rubber.component.DrawingMenuBar;
import io.github.unawarespecs.rubber.component.DrawingStatusPanel;
import io.github.unawarespecs.rubber.component.MainFrame;
import io.github.unawarespecs.rubber.component.PropertySheet;
import io.github.unawarespecs.rubber.model.DrawingState;
import io.github.unawarespecs.appfx.model.Shape;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
public class DrawingAppService implements AppService {

    private DrawingState drawingState;
    private MainFrame mainFrame;
    private PropertySheet propertySheet;
    private JPanel drawingPanel;
    private DrawingMenuBar drawingMenuBar;
    DrawingStatusPanel drawingStatusPanel;
    XmlDocumentService xmlDocumentService;
    int w = 5;
    public DrawingAppService(DrawingState drawingState){
        this.drawingState = drawingState;
    }

    @Override
    public void addShape(Shape shape) {
        drawingState.getShapes().add(shape);
        drawingPanel.repaint();
    }

    @Override
    public void updateShape(Shape shape) {
        Shape original = drawingState.getSelected();
        original = shape;
    }

    @Override
    public void deleteShape(Shape shape) {
        drawingState.getShapes().remove(shape);
        drawingState.setSelected(null);
        propertySheet.populateTable(this);
    }

    @Override
    public void setSelectedShape(Shape shape) {
        Shape lastSelected = drawingState.getSelected();
        if(lastSelected!=null){
            lastSelected.setSelected(false);
        }
        drawingState.setSelected(shape);
        if(shape != null) {
            shape.setSelected(true);
        }
        propertySheet.populateTable(this);
    }

    @Override
    public Shape getSelectedShape() {
       return drawingState.getSelectedShape();
    }

    @Override
    public ShapeMode getShapeMode() {
        return drawingState.getShapeMode();
    }

    @Override
    public void setShapeMode(ShapeMode  shapeMode ) {
        drawingState.setShapeMode(shapeMode);
    }

    @Override
    public Color getColor() {
        return drawingState.getColor();
    }
    @Override
    public void setColor(Color color){
        drawingState.setColor(color);
    }

    @Override
    public Color getFillColor(){
        return  drawingState.getFillColor();
    }

    @Override
    public void setFillColor(Color color){
        drawingState.setFillColor(color);
    }

    @Override
    public void setLineThickness(int thickness){
        drawingState.setLineThickness(thickness);
    }

    @Override
    public int getLineThickness() {
        return drawingState.getLineThickness();
    }

    @Override
    public List<Shape> getShapes(){
        return drawingState.getShapes();
    }

    @Override
    public String getImageFileName(){
        return drawingState.getImageFileName();
    }

    @Override
    public void setImageFileName(String imaageFileName){
        drawingState.setImageFileName(imaageFileName);
    }

    @Override
    public String getTextContent() {
        return drawingState.getTextContent();
    }

    @Override
    public void setTextContent(String text) {

    }

    @Override
    public Font getTextFont() {
        return drawingState.getTextFont();
    }

    @Override
    public void setTextFont(Font font) {

    }

    @Override
    public String getShapeImageFileName() {
        return null;
    }

    @Override
    public void setShapeImageFileName(String imageFileName) {

    }

    @Override
    public String getFileName(){

        return drawingState.getFilename();
    }

    @Override
    public void  setFileName(String filename){

        drawingState.setFilename(filename);
        setTitle(filename);
    }

    @Override
    public void open() {

        xmlDocumentService.open(drawingState.getFilename());
    }

    @Override
    public void save() {
        xmlDocumentService.save();
    }

    public void close(){
        drawingState.getShapes().clear();
        drawingPanel.repaint();
    }
    public void exit(){
        System.exit(0);
    }

    @Override
    public void repaint() {
        drawingPanel.repaint();
    }

    @Override
    public void setTitle(String title) {

        mainFrame.setTitle(title);
    }

    @Override
    public void setShapeXLocation(int x) {
    }

    @Override
    public void setShapeYLocation(int y) {

    }

    @Override
    public void setShapeXEnd(int x) {

    }

    @Override
    public void setShapeYEnd(int y) {

    }

    @Override
    public void undo() {
        CommandService.undo();
        drawingPanel.repaint();
    }

    @Override
    public void redo() {
        CommandService.redo();
    }
}


