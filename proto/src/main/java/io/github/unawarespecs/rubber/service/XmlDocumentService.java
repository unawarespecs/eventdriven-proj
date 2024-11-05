package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.model.Rectangle;
import io.github.unawarespecs.rubber.model.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;

public class XmlDocumentService implements DocumentService {

    DrawingState drawingState;

    public XmlDocumentService(DrawingState drawingState) {
        this.drawingState = drawingState;
    }

    @Override
    public void save() {
        saveAs(drawingState.getFilename());
    }

    @Override
    public void saveAs(String filename) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("Drawing");

            Attr attr = document.createAttribute("color");
            attr.setValue(String.valueOf(drawingState.getColor()));
            root.setAttributeNode(attr);

            attr = document.createAttribute("filename");
            attr.setValue(drawingState.getFilename());
            root.setAttributeNode(attr);

            attr = document.createAttribute("thickness");
            attr.setValue(String.valueOf(drawingState.getLineThickness()));
            root.setAttributeNode(attr);

            attr = document.createAttribute("fill");
            attr.setValue(String.valueOf(drawingState.getFillColor()));
            root.setAttributeNode(attr);

            document.appendChild(root);

            for (Shape shape : drawingState.getShapes()) {
                String shapeType = "";
                Element element = document.createElement("Shape");
                attr = document.createAttribute("type");
                if (shape.getClass() == Line.class) {
                    shapeType = "Line";
                } else if (shape.getClass() == Ellipse.class) {
                    shapeType = "Ellipse";
                } else if (shape.getClass() == Rectangle.class) {
                    shapeType = "Rectangle";
                } else if (shape.getClass() == Picture.class) {
                    shapeType = "Picture";
                }
                attr.setValue(shapeType);

                element.setAttributeNode(attr);
                attr = document.createAttribute("start.x");
                attr.setValue(String.valueOf(shape.getLocation().getX()));
                element.setAttributeNode(attr);
                attr = document.createAttribute("start.y");
                attr.setValue(String.valueOf(shape.getLocation().getY()));
                element.setAttributeNode(attr);
                attr = document.createAttribute("end.x");
                attr.setValue(String.valueOf(shape.getEnd().getX()));
                element.setAttributeNode(attr);
                attr = document.createAttribute("end.y");
                attr.setValue(String.valueOf(shape.getEnd().getY()));
                element.setAttributeNode(attr);
                attr = document.createAttribute("color");
                attr.setValue(String.valueOf(shape.getColor()));
                element.setAttributeNode(attr);

                if (shape.getClass() == Picture.class) {
                    attr = document.createAttribute("image_filename");
                    attr.setValue(((Picture) shape).getImageFilename());
                } else {
                    attr = document.createAttribute("fill");
                    attr.setValue(String.valueOf(shape.getFill()));
                }
                element.setAttributeNode(attr);

                attr = document.createAttribute("thickness");
                attr.setValue(String.valueOf(shape.getLineThickness()));
                element.setAttributeNode(attr);
                root.appendChild(element);
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

    @Override
    public void open(String filename) {
        drawingState.getShapes().clear();
        Shape shape = null;
        File xmlFile = new File(filename);
        int x;
        int y;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            NodeList nodeList = document.getElementsByTagName("Shape");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NamedNodeMap map = node.getAttributes();


                Node attr = map.getNamedItem("start.x");
                x = (int) Double.parseDouble(attr.getNodeValue());
                attr = map.getNamedItem("start.y");
                y = (int) Double.parseDouble(attr.getNodeValue());
                Point start = new Point(x, y);

                attr = map.getNamedItem("end.x");
                x = (int) Double.parseDouble(attr.getNodeValue());
                attr = map.getNamedItem("end.y");
                y = (int) Double.parseDouble(attr.getNodeValue());
                Point end = new Point(x, y);

                attr = map.getNamedItem("color");
                Color color = convertColor(attr.getNodeValue());

                // Initialize fill to a default value
                Color fill = null;
                attr = map.getNamedItem("fill");
                if (attr != null) {
                    fill = convertColor(attr.getNodeValue());
                }

                attr = map.getNamedItem("thickness");
                int thickness = Integer.parseInt(attr.getNodeValue());

                String imageFilename = null;
                attr = map.getNamedItem("image_filename");
                if (attr != null) {
                    imageFilename = attr.getNodeValue();
                }

                attr = map.getNamedItem("type");
                switch (attr.getNodeValue()) {
                    case "Rectangle" -> shape = new Rectangle(start, end, color, fill, thickness);
                    case "Ellipse" -> shape = new Ellipse(start, end, color, fill, thickness);
                    case "Line" -> shape = new Line(start, end, color, thickness);
                    case "Picture" -> {
                        if (imageFilename != null) {
                            shape = new Picture(start, end, color, imageFilename, thickness);
                        } else {
                            throw new IllegalArgumentException("Error: Picture shape missing image_filename attribute.");
                        }
                    }
                    default -> System.out.println("Unknown shape type: " + attr.getNodeValue());
                }
                drawingState.getShapes().add(shape);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public Color convertColor(String colorStr) {
//        Color color;
//        int rIndex = colorStr.indexOf("r=");
//        colorStr = colorStr.substring(rIndex + 2);
//        int nextIndex = colorStr.indexOf(",");
//        String rStr = colorStr.substring(0, nextIndex);
//        colorStr = colorStr.substring(nextIndex + 3);
//        nextIndex = colorStr.indexOf(",");
//        String gStr = colorStr.substring(0, nextIndex);
//        colorStr = colorStr.substring(nextIndex + 3);
//        nextIndex = colorStr.indexOf("]");
//        String bStr = colorStr.substring(0, nextIndex);
//        int rcolor = Integer.parseInt(rStr);
//        int gcolor = Integer.parseInt(gStr);
//        int bcolor = Integer.parseInt(bStr);
//        color = new Color(rcolor, gcolor, bcolor);
//        return color;
//    }

    public Color convertColor(String colorStr) {
        Color fallbackColor = Color.WHITE; // You can choose any color you want

        // Check for null or "null" string
        if (colorStr == null || colorStr.equals("null")) {
            return fallbackColor;
        }
        // Check if the string starts with the expected prefix
        if (!colorStr.startsWith("java.awt.Color[")) {
            throw new IllegalArgumentException("Invalid color string format");
        }

        try {
            int rIndex = colorStr.indexOf("r=");
            int gIndex = colorStr.indexOf("g=");
            int bIndex = colorStr.indexOf("b=");

            if (rIndex == -1 || gIndex == -1 || bIndex == -1) {
                throw new IllegalArgumentException("Missing RGB components");
            }

            // Extract the RGB values
            String rStr = colorStr.substring(rIndex + 2, colorStr.indexOf(",", rIndex));
            String gStr = colorStr.substring(gIndex + 2, colorStr.indexOf(",", gIndex));
            String bStr = colorStr.substring(bIndex + 2, colorStr.indexOf("]", bIndex));

            // Parse the RGB values
            int rcolor = Integer.parseInt(rStr);
            int gcolor = Integer.parseInt(gStr);
            int bcolor = Integer.parseInt(bStr);

            // Create and return the Color object
            return new Color(rcolor, gcolor, bcolor);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("RGB values must be integers between 0 and 255");
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid color string format");
        }
    }

}
