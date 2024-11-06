package io.github.unawarespecs.rubber.component;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.enums.Vertex;
import io.github.unawarespecs.appfx.model.SearchResult;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.appfx.service.SearchService;
import io.github.unawarespecs.rubber.ShapeToolbar;
import io.github.unawarespecs.rubber.command.renderer.EllipseRenderer;
import io.github.unawarespecs.rubber.command.renderer.LineRenderer;
import io.github.unawarespecs.rubber.command.renderer.RectangleRenderer;
import io.github.unawarespecs.rubber.model.*;
import io.github.unawarespecs.rubber.model.Ellipse;
import io.github.unawarespecs.rubber.model.Line;
import io.github.unawarespecs.rubber.model.Picture;
import io.github.unawarespecs.rubber.model.Rectangle;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.model.Text;
import io.github.unawarespecs.rubber.service.*;
import io.github.unawarespecs.appfx.service.Renderer;
import io.github.unawarespecs.rubber.service.SearchServiceImpl;
import io.github.unawarespecs.rubber.service.ShapeMover;
import lombok.Data;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

@Data
public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    Renderer lineRenderer;
    Renderer ellipseRenderer;
    Renderer rectangleRenderer;
    SearchService searchService;
    ShapeMover moverService;
    ShapeToolbar shapeToolbar;
    ShapeMode shapeMode;
    Point start;
    Point end;
    Point prev;
    Color color;
    int lineThickness;
    SearchResult result;
    JButton lineButton;
    JButton rectButton;
    JButton ellipseButton;
    boolean drawn = false;
    Shape shape;
    private DrawingStatusPanel drawingStatusPanel;
    private AppService appService;

    public DrawingPanel(AppService appService) {
        super(new BorderLayout());
        this.appService = appService;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        color = Color.CYAN;

        searchService = new SearchServiceImpl(appService);
        lineRenderer = new LineRenderer();
        ellipseRenderer = new EllipseRenderer();
        rectangleRenderer = new RectangleRenderer();
        shapeToolbar = new ShapeToolbar();
        moverService = new ShapeMover();
        lineButton = new JButton();
        var icon = new ImageIcon("src/resources/exit2.png");
        rectButton = new JButton();
        ellipseButton = new JButton();
        setOpaque(false);
        drawn = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Shape shape : appService.getShapes()) {
            shape.getRenderer().render(g, shape, false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = e.getPoint();
        System.out.println("mousePressed start.x = " + String.valueOf(start.x) + " start.y = " + String.valueOf(start.y));
        end = start;
        shapeMode = appService.getShapeMode();
        lineThickness = appService.getLineThickness();
        color = appService.getColor();
        Color fill = appService.getFillColor();
        String text = appService.getTextContent();
        Font font = appService.getTextFont();

        if (shapeMode == ShapeMode.Line) {
            shape = new Line(start, start, color, lineThickness);
        } else if (shapeMode == ShapeMode.Rectangle) {
            shape = new Rectangle(start, start, color, fill, lineThickness);
        } else if (shapeMode == ShapeMode.Ellipse) {
            shape = new Ellipse(start, start, color, fill, lineThickness);
        } else if (shapeMode == ShapeMode.Image) {
            shape = new Picture(start, start, color, appService.getImageFileName(), lineThickness);
        } else if (shapeMode == ShapeMode.Text) {
            shape = new Text(start, start, text, font, font.getSize(), color, lineThickness);
        } else if (shapeMode == ShapeMode.Select) {
            result = searchService.search(start);
            if (result != null) {
                appService.setSelectedShape(result.getShape());
            } else {
                appService.setSelectedShape(null);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shapeMode = appService.getShapeMode();
        Point prevEnd = end;
        end = e.getPoint();
        Graphics g = getGraphics();
        if (shapeMode == ShapeMode.Select) {
            if (result != null) {
                moverService.move(g, shape, prevEnd, end);
                repaint();
                result = null;
            }
        } else {
            moverService.scale(g, shape, end);
            appService.addShape(shape);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        shapeMode = appService.getShapeMode();
        drawingStatusPanel.setPoint(e.getPoint());
        Point prevEnd = end;
        end = e.getPoint();
        Graphics g = getGraphics();
        shapeMode = appService.getShapeMode();
        if (shapeMode == ShapeMode.Select) {
            if (result != null) {
                shape = result.getShape();
                if (result.getVertex() == Vertex.Whole) {
                    moverService.move(g, shape, prevEnd, end);
                }
            }
        } else {
            moverService.scale(g, shape, end);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        drawingStatusPanel.setPoint(e.getPoint());
    }

    public void save(String filename) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("Drawing");
            document.appendChild(root);

            for (Shape shape : appService.getShapes()) {
                if (shape.getClass() == Line.class) {
                    Element element = document.createElement("Line");
                    Attr attr = document.createAttribute("x");
                    element.setAttributeNode(attr);
                } else if (shape.getClass() == Ellipse.class) {
                    Element element = document.createElement("Ellipse");
                    Attr attr = document.createAttribute("x");
                    element.setAttributeNode(attr);
                } else if (shape.getClass() == Rectangle.class) {
                    Element element = document.createElement("Rectangle");
                    Attr attr = document.createAttribute("x");
                    element.setAttributeNode(attr);
                }
            }

            // Write to XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            // Specify your local file path
            StreamResult result = new StreamResult(filename);
            transformer.transform(source, result);

            System.out.println("XML file created successfully!");
        } catch (Exception e) {
            System.out.println("Error occurred " + e.getMessage());
        }
    }
}
