package io.github.unawarespecs.rubber.component;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;

import io.github.unawarespecs.rubber.command.renderer.TextToolRenderer;
import io.github.unawarespecs.rubber.model.Ellipse;
import io.github.unawarespecs.rubber.model.Line;
import io.github.unawarespecs.rubber.model.Picture;
import io.github.unawarespecs.rubber.model.Rectangle;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.model.Text;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class DrawingToolBar extends JToolBar implements ActionListener {
    static final private String RECT = "rectangle";
    static final private String LINE = "Line";
    static final private String ELLIPSE = "ellipse";
    static final private String TEXT = "text";
    static final private String CHAIN = "chain";
    static final private String IMAGE = "image";
    static final private String SELECT = "select";
    static final private String SOMETHING_ELSE = "other";
    static final private String TEXT_ENTERED = "text_entered";
    protected JTextArea textArea;
    protected String newline = "\n";
    private AppService appService;

    public DrawingToolBar(AppService appService) {
        this.appService = appService;
        addButtons();
        setFloatable(false);
        setRollover(true);

        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        //Lay out the main panel.
        setPreferredSize(new Dimension(200, 30));
        setBackground(Color.GREEN);
    }

    private static JFileChooser summonFileChooser(String homeFolder) {
        JFileChooser fileChooser = new JFileChooser(homeFolder);
        FileTypeFilter pngTypeFilter = new FileTypeFilter("png", "PNG Image Documents");
        FileTypeFilter jpgTypeFilter = new FileTypeFilter("jpg", "JPEG Image Documents");
        FileTypeFilter gifTypeFilter = new FileTypeFilter("gif", "GIF Image Documents");
        fileChooser.addChoosableFileFilter(pngTypeFilter);
        fileChooser.addChoosableFileFilter(jpgTypeFilter);
        fileChooser.addChoosableFileFilter(gifTypeFilter);
        return fileChooser;
    }

    protected void addButtons() {
        JButton button = null;
        button = makeNavigationButton("rect", RECT, "Draw a rectangle", "Rectangle");
        add(button);

        button = makeNavigationButton("line", LINE, "Draw a line", "Line");
        add(button);

        button = makeNavigationButton("ellipse", ELLIPSE, "Draw an ellipse", "Ellipse");
        add(button);

        button = makeNavigationButton("text", TEXT, "Add a text", "Text");
        add(button);

        button = makeNavigationButton("chain", CHAIN, "Add a sequence of lines", "Chain");
        add(button);

        button = makeNavigationButton("image", IMAGE, "Add an  image", "Image");
        add(button);

        button = makeNavigationButton("select", SELECT, "Switch to select", "Select");
        add(button);

        //separator
        addSeparator();

        //fourth button
        button = new JButton("Another button");
        button.setActionCommand(SOMETHING_ELSE);
        button.setToolTipText("Something else");
        button.addActionListener(this);
        add(button);

        //fifth component is NOT a button!
        JTextField textField = new JTextField("");
        textField.setColumns(10);
        textField.addActionListener(this);
        textField.setActionCommand(TEXT_ENTERED);
        add(textField);
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = "images/"
                + imageName
                + ".png";
        URL imageURL = DrawingToolBar.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                    + imgLocation);
        }

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String description = null;

        // Handle each button.
        if (RECT.equals(cmd)) { //first button clicked
            appService.setShapeMode(ShapeMode.Rectangle);
        } else if (LINE.equals(cmd)) { // second button clicked
            appService.setShapeMode(ShapeMode.Line);
        } else if (ELLIPSE.equals(cmd)) { // third button clicked
            appService.setShapeMode(ShapeMode.Ellipse);
        } else if (TEXT.equals(cmd)) { // third button clicked
            appService.setShapeMode(ShapeMode.Text);
        } else if (CHAIN.equals(cmd)) { // third button clicked
            appService.setShapeMode(ShapeMode.Chain);
        } else if (IMAGE.equals(cmd)) { // third button
            JFileChooser fileChooser = getChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                appService.setImageFileName(filename);
                appService.setShapeMode(ShapeMode.Image);
            }
        } else if (SELECT.equals(cmd)) { // third button clicked
            appService.setShapeMode(ShapeMode.Select);
        } else if (SOMETHING_ELSE.equals(cmd)) { // fourth button clicked
            description = "done something else.";
        } else if (TEXT_ENTERED.equals(cmd)) { // text field
            JTextField tf = (JTextField) e.getSource();
            String text = tf.getText();
            parseCommand(text);
            tf.setText("");
            description = "done something with this text: "
                    + newline + "  \""
                    + text + "\"";
        }

        displayResult("If this were a real app, it would have "
                + description);
    }

    private JFileChooser getChooser() {
        String homeFolder;
        if (appService.getImageFileName() == null) {
            homeFolder = FileSystemView.getFileSystemView().getHomeDirectory().getPath();
        } else {
            File file = new File(appService.getImageFileName());
            homeFolder = file.getPath();
        }
        return summonFileChooser(homeFolder);
    }

    protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + newline);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

//    void parseCommand(String str) {
//        String cmd = str.toUpperCase();
//        if (str.startsWith("SET")) {
//            setCmd(str);
//        } else if (startsWithNumber(str.substring(0, 1))){
//        addCmd(str);
//    }
//
//            // if SET-RECT
//        appService.setShapeMode(ShapeMode.Rectangle);
//    }
//    void setCmd(String str){
//         String setStr =  str.substring(0,4);
//         String shapeStr = str.substring(4);
//         // Insert code to
//        // 1. create SetShapeComand using the s=correct Shapemode
//         // 2. Call ExecuteCommand of the CommandService
//
//
//    }
//
//    void addCmd(String str){
//
//
//    }

    void parseCommand(String str) {
        String commandString = str.toUpperCase();

        String[] parts = commandString.split(" ");
        String action = parts[0];

        switch (action) {
            case "SET":
                System.out.println("Set cmd detected");
                setCmd(parts);
                break;
            case "ADD":
                System.out.println("Add shape detected");
                addCmd(parts);
                break;
            default:
                errorDialogBox("Unknown command: " + commandString, "Error");
                throw new UnsupportedOperationException("unknown command: " + commandString);
        }
    }

    private void setCmd(String[] parts) {
        for (int i = 1; i < parts.length; i++) {
            String[] keyValue = parts[i].split("=");
            String key = keyValue[0];

            switch (key) {
                case "COLOR":
                    String color_str = keyValue[1];
                    Color newColor = parseColor(color_str);
                    appService.setColor(newColor);
                    System.out.println("color set to: " + newColor);
                    break;
                case "FILLCOLOR":
                    String rgb_fill = keyValue[1];
                    Color fillColor = parseColor(rgb_fill);
                    appService.setFillColor(fillColor);
                    System.out.println("fill color set to: " + fillColor);
                    break;
                case "THICKNESS":
                    int thickness = Integer.parseInt(keyValue[1]);
                    appService.setLineThickness(thickness);
                    System.out.println("line thickness set to " + thickness + " pixels");
                    break;
                case "SHAPE":
                    String called_shape = keyValue[1];
                    switch (called_shape) {
                        case "LINE":
                            appService.setShapeMode(ShapeMode.Line);
                            shapeSetDialogBox("Shape set to " + ShapeMode.Line);
                            break;
                        case "RECTANGLE":
                            appService.setShapeMode(ShapeMode.Rectangle);
                            shapeSetDialogBox("Shape set to " + ShapeMode.Rectangle);
                            break;
                        case "TEXT":
                            appService.setShapeMode(ShapeMode.Text);
                            shapeSetDialogBox("Shape set to " + ShapeMode.Text);
                            break;
                        case "ELLIPSE":
                            appService.setShapeMode(ShapeMode.Ellipse);
                            shapeSetDialogBox("Shape set to " + ShapeMode.Ellipse);
                            break;
                        case "CHAIN":
                            appService.setShapeMode(ShapeMode.Chain);
                            shapeSetDialogBox("Shape set to " + ShapeMode.Chain);
                            break;
                        case "IMAGE":
                            appService.setShapeMode(ShapeMode.Image);
                            shapeSetDialogBox("Shape set to " + ShapeMode.Image);
                            break;
                        default:
                            errorDialogBox("Unknown shape: " + called_shape, "Shape Not Present");
                            throw new UnsupportedOperationException("unknown shape: " + called_shape);
                    }
                    break;
                default:
                    errorDialogBox("Unknown attribute: " + key, "Error");
                    throw new IllegalArgumentException("Unknown attribute: " + key);
            }
        }
    }

    private void addCmd(String[] parts) {
        String shapeType = parts[1];
        String[] coordinates = parts[2].split("-");
        String[] start = coordinates[0].split(",");
        String[] end = coordinates[1].split(",");

        int startX = Integer.parseInt(start[0]);
        int startY = Integer.parseInt(start[1]);
        int endX = Integer.parseInt(end[0]);
        int endY = Integer.parseInt(end[1]);

        Shape shape;
        if (shapeType.equals("LINE")) {
            shape = new Line(
                    new Point(startX, startY),
                    new Point(endX, endY),
                    appService.getColor(),
                    appService.getLineThickness()
            );

            appService.addShape(shape);
            coordinateStringPrint(ShapeMode.Line, startX, startY, endX, endY);
        } else if (shapeType.equals("RECT") | shapeType.equals("RECTANGLE")) {
            shape = new Rectangle(
                    new Point(startX, startY),
                    new Point(endX, endY),
                    appService.getColor(),
                    appService.getFillColor(),
                    appService.getLineThickness()
            );
            appService.addShape(shape);
            coordinateStringPrint(ShapeMode.Rectangle, startX, startY, endX, endY);

        } else if (shapeType.equals("ELLIPSE") | shapeType.equals("CIRCLE")) {
            shape = new Ellipse(
                    new Point(startX, startY),
                    new Point(endX, endY),
                    appService.getColor(),
                    appService.getFillColor(),
                    appService.getLineThickness()
            );

            appService.addShape(shape);
            coordinateStringPrint(ShapeMode.Ellipse, startX, startY, endX, endY);
        } else if (shapeType.equals("IMAGE")) {
            JFileChooser fileChooser = getChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                appService.setImageFileName(filename);
            }
            shape = new Picture(
                    new Point(startX, startY),
                    new Point(endX, endY),
                    appService.getColor(),
                    appService.getImageFileName(),
                    appService.getLineThickness()
            );
            appService.addShape(shape);
            coordinateStringPrint(ShapeMode.Image, startX, startY, endX, endY);
        } else if (shapeType.equals("TEXT")) {
//            String text = appService.getTextContent();
            String text = "test";
            Font font = appService.getTextFont();
            int font_size = appService.getTextFont().getSize();

            shape = new Text(
                    new Point(startX, startY),
                    new Point(endX, endY),
                    text,
                    font,
                    font_size,
                    appService.getColor(),
                    appService.getLineThickness()
            );

            appService.addShape(shape);
            coordinateStringPrint(ShapeMode.Text, startX, startY, endX, endY);
        } else if (shapeType.equals("CHAIN")) {
            errorDialogBox("This shape is present, but drawing thru command line is not yet implemented [FIXME].",
                    "Shape Not Implemented");
            throw new UnsupportedOperationException("not supported yet for these shape types");
        }
    }

    void errorDialogBox(String msg, String title) {
        JOptionPane.showMessageDialog(null,
                msg,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    void shapeSetDialogBox(String msg) {
        JOptionPane.showMessageDialog(null,
                msg,
                "Shape set",
                JOptionPane.INFORMATION_MESSAGE);
    }

    void coordinateStringPrint(ShapeMode shape_type, int x1, int y1, int x2, int y2) {
        System.out.println(
                shape_type + " drawn at: " +
                        "start(" + x1 + "," + y1 + "), " +
                        "end(" + x2 + "," + y2 + ")"
        );
    }

    private Color parseColor(String colorString) {
        try {
            String[] rgb = colorString.split(",");
            return new Color(
                    Integer.parseInt(rgb[0]),
                    Integer.parseInt(rgb[1]),
                    Integer.parseInt(rgb[2])
            );
        } catch (IllegalArgumentException e) {
            errorDialogBox("Error: " + e, "Error");
            throw new IllegalArgumentException("Invalid color format: " + colorString);
        }
    }

    boolean startsWithNumber(String str) {
        try {
            int t = Integer.parseInt(str.substring(0, 1));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean match(String basis, String str, int start, int len) {
        return str.substring(start, len).equals(basis);
    }
}



