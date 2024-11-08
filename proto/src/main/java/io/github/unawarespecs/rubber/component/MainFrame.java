package io.github.unawarespecs.rubber.component;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.property.PropertyOptions;
import io.github.unawarespecs.property.event.PropertyEventAdapter;
import io.github.unawarespecs.property.property.*;
import io.github.unawarespecs.property.property.Property;
import io.github.unawarespecs.rubber.model.DrawingState;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.service.DrawingAppService;
import io.github.unawarespecs.rubber.service.DrawingCommandAppService;
import io.github.unawarespecs.rubber.service.XmlDocumentService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


    private DrawingPanel drawingPanel;

    private DrawingMenuBar drawingMenuBar;

    private DrawingToolBar drawingToolBar;

    private DrawingStatusPanel drawingStatusPanel;

    DrawingState drawingState;

    AppService appService;

    private PropertySheet propertySheet;
    private JScrollPane scrollPane;


    private final int PREFERRED_WIDTH = 500;
    private final int PREFERRED_HEIGHT = 600;


    public MainFrame() {
        Container pane = this.getContentPane();
        drawingState = new DrawingState();
        DrawingAppService drawAppService = new DrawingAppService(drawingState);
        appService = new DrawingCommandAppService(drawAppService);
        drawingMenuBar = new DrawingMenuBar(appService);


        drawingPanel = new DrawingPanel(appService);
        drawingToolBar = new DrawingToolBar(appService);
        drawingStatusPanel = new DrawingStatusPanel(appService);
        drawingStatusPanel.setPreferredSize(new Dimension(getWidth(), 32));
        drawingStatusPanel.setVisible(true);


        JScrollPane jScrollPane = new JScrollPane(drawingPanel);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        drawingPanel.setPreferredSize(new Dimension(4095, 8192));
        drawingPanel.setDrawingStatusPanel(drawingStatusPanel);
        setLayout(new BorderLayout());
        setJMenuBar(drawingMenuBar);
        buildGUI(pane);

        XmlDocumentService xmlDocumentService =  new XmlDocumentService(drawingState);

        drawAppService.setPropertySheet(propertySheet);
        drawAppService.setDrawingStatusPanel(drawingStatusPanel);
        drawAppService.setDrawingMenuBar(drawingMenuBar);
        drawAppService.setMainFrame(this);
        drawAppService.setDrawingPanel(drawingPanel);
        drawAppService.setXmlDocumentService(xmlDocumentService);

        pane.add(drawingToolBar, BorderLayout.PAGE_START);
        pane.add(drawingStatusPanel, BorderLayout.PAGE_END);

        getContentPane().add(jScrollPane, BorderLayout.CENTER);
        this.setTitle("Go Draw (New)");

    }

    public void buildGUI(Container pane) {
        buildPropertyTable(pane);
        scrollPane = new JScrollPane(propertySheet);
        pane.add(scrollPane, BorderLayout.LINE_END);
        pack();
    }

    void buildPropertyTable(Container pane) {
        String[] headers = new String[]{"Property", "value"};
        Color backgroundColor = new Color(255, 255, 255);
        Color invalidColor = new Color(255, 179, 176);
        int rowHeight = 30;
        PropertyOptions options = new PropertyOptions(headers, backgroundColor, invalidColor, rowHeight);

        propertySheet = new PropertySheet(new PropertyOptions.Builder().build());
        propertySheet.addEventListener(new EventListener());
        propertySheet.populateTable(appService);

        repaint();
    }

    class EventListener extends PropertyEventAdapter {
        @Override
        public void onPropertyUpdated(Property property) {
            Shape shape  = drawingState.getSelected();
            if(property.getName().equals("Current Shape")){
                if(shape ==null) {
                    drawingState.setShapeMode((ShapeMode) property.getValue());
                }
            }
            if(property.getName().equals("Fore color")){
                if(shape ==null) {
                    drawingState.setColor((Color) property.getValue());
                } else {
                    shape.setColor((Color) property.getValue());
                }
            }
            if(property.getName().equals("Fill color")){
                if(shape ==null) {
                drawingState.setFillColor((Color)property.getValue());
                } else {
                    shape.setFill((Color) property.getValue());
                }
            }
            if(property.getName().equals("Line Thickness")){
                if(shape ==null) {
                    drawingState.setLineThickness((int)property.getValue());
                } else {
                    shape.setLineThickness((int) property.getValue());
                }
            }
            if(property.getName().equals("X Location")){
                if(shape ==null) {
                    ;
                } else {
                    Point p = shape.getLocation();
                    p.x = (int) property.getValue();
                    shape.setLocation(p);
                }
            }
            if(property.getName().equals("Y Location")){
                if(shape ==null) {
                    ;
                } else {
                    Point p = shape.getLocation();
                    p.y = (int) property.getValue();
                    shape.setLocation(p);
                }
            }
            if(property.getName().equals("X End")){
                if(shape ==null) {
                    ;
                } else {
                    Point p = shape.getEnd();
                    p.x = (int) property.getValue();
                    shape.setEnd(p);
                }
            }
            if(property.getName().equals("Y End")){
                if(shape ==null) {
                    ;
                } else {
                    Point p = shape.getEnd();
                    p.y = (int) property.getValue();
                    shape.setEnd(p);
                }
            }

            drawingPanel.repaint();
        }
    }
}
