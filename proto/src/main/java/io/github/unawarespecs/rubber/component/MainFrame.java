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

    DrawingState drawingState;
    AppService appService;
    private DrawingPanel drawingPanel;
    private DrawingMenuBar drawingMenuBar;
    private DrawingToolBar drawingToolBar;
    private DrawingStatusPanel drawingStatusPanel;
    private PropertySheet propertySheet;
    private JScrollPane scrollPane;

    public MainFrame() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(this.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

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
        int PREFERRED_HEIGHT = 900;
        int PREFERRED_WIDTH = 1600;
        drawingPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        drawingPanel.setDrawingStatusPanel(drawingStatusPanel);
        setLayout(new BorderLayout());
        setJMenuBar(drawingMenuBar);
        buildGUI(pane);

        XmlDocumentService xmlDocumentService = new XmlDocumentService(drawingState);

        drawAppService.setPropertySheet(propertySheet);
        drawAppService.setDrawingStatusPanel(drawingStatusPanel);
        drawAppService.setDrawingMenuBar(drawingMenuBar);
        drawAppService.setMainFrame(this);
        drawAppService.setDrawingPanel(drawingPanel);
        drawAppService.setXmlDocumentService(xmlDocumentService);

        pane.add(drawingToolBar, BorderLayout.PAGE_START);
        pane.add(drawingStatusPanel, BorderLayout.PAGE_END);

        getContentPane().add(jScrollPane, BorderLayout.CENTER);
        this.setTitle("Go Draw - New");



    }

    public void buildGUI(Container pane) {
        buildPropertyTable(pane);
        scrollPane = new JScrollPane(propertySheet);
        pane.add(scrollPane, BorderLayout.LINE_END);
        pack();
    }

    void buildPropertyTable(Container pane) {
        String[] headers = new String[]{"Property", "Value"};
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
            Shape shape = drawingState.getSelected();
            if (property.getName().equals("Current Shape")) {
                if (shape == null) {
                    drawingState.setShapeMode((ShapeMode) property.getValue());
                }
            }
            if (property.getName().equals("Fore color")) {
                if (shape == null) {
                    drawingState.setColor((Color) property.getValue());
                } else {
                    shape.setColor((Color) property.getValue());
                }
            }
            if (property.getName().equals("Fill color")) {
                if (shape == null) {
                    drawingState.setFillColor((Color) property.getValue());
                } else {
                    shape.setFill((Color) property.getValue());
                }
            }
            if (property.getName().equals("Line Thickness")) {
                if (shape == null) {
                    drawingState.setLineThickness((int) property.getValue());
                } else {
                    shape.setLineThickness((int) property.getValue());
                }
            }
            if (property.getName().equals("X Location")) {
                assert shape != null;
                Point p = shape.getLocation();
                p.x = (int) property.getValue();
                shape.setLocation(p);
            }
            if (property.getName().equals("Y Location")) {
                assert shape != null;
                Point p = shape.getLocation();
                p.y = (int) property.getValue();
                shape.setLocation(p);
            }
            if (property.getName().equals("X End")) {
                assert shape != null;
                Point p = shape.getEnd();
                p.x = (int) property.getValue();
                shape.setEnd(p);
            }
            if (property.getName().equals("Y End")) {
                assert shape != null;
                Point p = shape.getEnd();
                p.y = (int) property.getValue();
                shape.setEnd(p);
            }

            drawingPanel.repaint();
        }
    }
}
