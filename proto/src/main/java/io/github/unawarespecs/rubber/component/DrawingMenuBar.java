package io.github.unawarespecs.rubber.component;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.CommandService;

import io.github.unawarespecs.fontchooser.FontDialog;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;

public class DrawingMenuBar extends JMenuBar implements ActionListener {

    @Getter
    @Setter
    BasicStroke basicStroke = new BasicStroke(10);
    private AppService appService;
    private JMenuItem openMenuItem = new JMenuItem("Open");
    private JMenuItem saveMenuItem = new JMenuItem("Save");
    private JMenuItem saveAsMenuItem = new JMenuItem("Save as...");
    private JMenuItem closeMenuItem = new JMenuItem("Close File");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");
    private JMenuItem undoMenuItem = new JMenuItem("Undo");
    private JMenuItem redoMenuItem = new JMenuItem("Redo");
    private JMenuItem thinLineMenuItem = new JMenuItem("Thin");
    private JMenuItem mediumLineMenuItem = new JMenuItem("Medium");
    private JMenuItem thickLineMenuItem = new JMenuItem("Thick");
    private JMenuItem colorMenu = new JMenu("Color");
    private JMenuItem foreColorMenuItem = new JMenuItem("Foreground");
    private JMenuItem fillColorMenuItem = new JMenuItem("FillColor");
    private JMenuItem lineMenuItem = new JMenuItem("Line");
    private JMenuItem ellipseMenuItem = new JMenuItem("Ellipse");
    private JMenuItem rectangleMenuItem = new JMenuItem("Rectangle");
    private JMenuItem curveMenuItem = new JMenuItem("Curve");
    private JMenuItem textMenuItem = new JMenuItem("Text Tool");
    private JMenuItem imageMenuItem = new JMenuItem("Insert Picture/Image");
    private JMenuItem selectMenuItem = new JMenuItem("Select");
    private JMenuItem fontMenuItem = new JMenuItem("Choose Font");
    private JMenuItem customLineThicknessMenuItem = new JMenuItem("Custom Line Thickness");
    private JMenuItem exportAsImageItem = new JMenuItem("Export as PNG...");
    private java.util.Map<Object, Runnable> actionMap = new HashMap<>();
    @Getter
    @Setter
    private ShapeMode shapeMode = ShapeMode.Line;

    public DrawingMenuBar(AppService appService) {
        super();
        this.appService = appService;

        initializeActionMap(); // for menu bar items.

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        JMenu drawMenu = new JMenu("Draw");
        drawMenu.setMnemonic(KeyEvent.VK_D);
        JMenu attributeMenu = new JMenu("Attributes");
        attributeMenu.setMnemonic(KeyEvent.VK_A);

        JMenu thicknessLineMenu = new JMenu("Thickness");
        thicknessLineMenu.setMnemonic(KeyEvent.VK_T);

        KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyStrokeToExport = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
        KeyStroke keyStrokeToSaveAs = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
        KeyStroke keyStrokeToUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyStrokeToRedo = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyStrokeToQuit = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);

        this.add(fileMenu);
        fileMenu.add(openMenuItem);
        openMenuItem.setMnemonic(KeyEvent.VK_O);
        openMenuItem.setAccelerator(keyStrokeToOpen);
        fileMenu.add(saveMenuItem);
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.setAccelerator(keyStrokeToSave);
        fileMenu.add(saveAsMenuItem);
        saveAsMenuItem.setMnemonic(KeyEvent.VK_A);
        saveAsMenuItem.setAccelerator(keyStrokeToSaveAs);
        fileMenu.add(exportAsImageItem);
        exportAsImageItem.setMnemonic(KeyEvent.VK_E);
        exportAsImageItem.setAccelerator(keyStrokeToExport);
        fileMenu.add(closeMenuItem);
        closeMenuItem.setMnemonic(KeyEvent.VK_C);
        fileMenu.add(exitMenuItem);
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.setAccelerator(keyStrokeToQuit);

        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        saveAsMenuItem.addActionListener(this);
        exportAsImageItem.addActionListener(this);
        closeMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        editMenu.add(undoMenuItem);
        undoMenuItem.setAccelerator(keyStrokeToUndo);
        editMenu.add(redoMenuItem);
        redoMenuItem.setAccelerator(keyStrokeToRedo);

        this.add(editMenu);
        undoMenuItem.addActionListener(this);
        redoMenuItem.addActionListener(this);

        this.add(viewMenu);
        this.add(drawMenu);
        this.add(attributeMenu);

        viewMenu.add(selectMenuItem);
        selectMenuItem.addActionListener(this);

        attributeMenu.add(colorMenu);
        colorMenu.add(foreColorMenuItem);
        colorMenu.add(fillColorMenuItem);
        attributeMenu.add(fontMenuItem);
        foreColorMenuItem.addActionListener(this);
        fillColorMenuItem.addActionListener(this);
        fontMenuItem.addActionListener(this);

        attributeMenu.add(thicknessLineMenu);

        thicknessLineMenu.add(thinLineMenuItem);
        thicknessLineMenu.add(mediumLineMenuItem);
        thicknessLineMenu.add(thickLineMenuItem);
        thicknessLineMenu.add(customLineThicknessMenuItem);

        thinLineMenuItem.addActionListener(this);
        mediumLineMenuItem.addActionListener(this);
        thickLineMenuItem.addActionListener(this);
        customLineThicknessMenuItem.addActionListener(this);

        drawMenu.add(ellipseMenuItem);
        ellipseMenuItem.addActionListener(this);

        drawMenu.add(lineMenuItem);
        lineMenuItem.addActionListener(this);

        drawMenu.add(rectangleMenuItem);
        rectangleMenuItem.addActionListener(this);
        drawMenu.add(curveMenuItem);
        curveMenuItem.addActionListener(this);
        drawMenu.add(imageMenuItem);
        imageMenuItem.addActionListener(this);
        drawMenu.add(textMenuItem);
        textMenuItem.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Runnable action = actionMap.get(e.getSource());
        if (action != null) {
            action.run();
        }
    }

    private void initializeActionMap() {
        //shapes.
        actionMap.put(lineMenuItem, () -> appService.setShapeMode(ShapeMode.Line));
        actionMap.put(ellipseMenuItem, () -> appService.setShapeMode(ShapeMode.Ellipse));
        actionMap.put(rectangleMenuItem, () -> appService.setShapeMode(ShapeMode.Rectangle));
        actionMap.put(selectMenuItem, () -> appService.setShapeMode(ShapeMode.Select));
        actionMap.put(imageMenuItem, () -> {
            JFileChooser fileChooser = getChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                appService.setImageFileName(filename);
                appService.setShapeMode(ShapeMode.Image);
            }
        });
        actionMap.put(textMenuItem, () -> {
            String inputText = showTextInputDialog();
            if (inputText != null && !inputText.trim().isEmpty()) {
                appService.setTextContent(inputText);
                appService.setShapeMode(ShapeMode.Text);
            }
        });
        //thickness levels.
        actionMap.put(thinLineMenuItem, () -> appService.setLineThickness(1));
        actionMap.put(mediumLineMenuItem, () -> appService.setLineThickness(5));
        actionMap.put(thickLineMenuItem, () -> appService.setLineThickness(10));
        actionMap.put(customLineThicknessMenuItem, () -> {
            try {
                int customThickness = customThicknessDialog();
                appService.setLineThickness(customThickness);
            } catch (NumberFormatException e) {
                System.out.println(e.toString());
            }
        });

        //attributes
        actionMap.put(foreColorMenuItem, () -> {
            Color color = JColorChooser.showDialog(null, "Choose a color", appService.getColor());
            appService.setColor(color);
        });
        actionMap.put(fillColorMenuItem, () -> {
            Color color = JColorChooser.showDialog(null, "Choose a color", appService.getColor());
            appService.setFillColor(color);
        });
        actionMap.put(fontMenuItem, () -> {
            FontDialog.showDialog(this);
            Font font = FontDialog.previewFont;
            appService.setTextFont(font);
        });

        //undo-redo
        actionMap.put(undoMenuItem, () -> appService.undo());
        actionMap.put(redoMenuItem, CommandService::redo);

        //file menu
        actionMap.put(saveAsMenuItem, () -> {
            String filename = appService.getFileName();
            JFileChooser fileChooser = openChooser();
            fileChooser.addChoosableFileFilter(new FileFilter() {
                public String getDescription() {
                    return "Xml Documents (*.xml)";
                }

                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        return f.getName().toLowerCase().endsWith(".xml");
                    }
                }
            });
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                filename = fileChooser.getSelectedFile().getAbsolutePath();
                appService.setFileName(filename);
                appService.save();
            }

        });
        actionMap.put(saveMenuItem, () -> {
            String filename = appService.getFileName();
            if (filename == null) {
                JFileChooser c = openChooser();
                c.addChoosableFileFilter(new FileFilter() {
                    public String getDescription() {
                        return "Xml Documents (*.xml)";
                    }

                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        } else {
                            return f.getName().toLowerCase().endsWith(".xml");
                        }
                    }
                });
                int result = c.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // set the label to the path of the selected file
                    filename = c.getSelectedFile().getAbsolutePath();
                    appService.setFileName(filename);
                    appService.save();
                }
            }

        });
        actionMap.put(openMenuItem, () -> {
            String filename = appService.getFileName();
            JFileChooser fileChooser = openChooser();
            FileTypeFilter xmlTypeFilter = new FileTypeFilter("xml", "Xml Documents");
            fileChooser.addChoosableFileFilter(xmlTypeFilter);
            fileChooser.setFileFilter(xmlTypeFilter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                filename = fileChooser.getSelectedFile().getAbsolutePath();
                appService.setFileName(filename);
            }
            appService.open();
            appService.repaint();
        });
        actionMap.put(closeMenuItem, () -> appService.close());
        actionMap.put(exitMenuItem, () -> appService.exit());
        actionMap.put(exportAsImageItem, () -> {
            String filename;
            FileTypeFilter pngTypeFilter = new FileTypeFilter("png", "PNG Image");
            JFileChooser c = openChooser();
            c.addChoosableFileFilter(new FileFilter() {
                public String getDescription() {
                    return "PNG Image (*.png)";
                }

                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        return f.getName().toLowerCase().endsWith(".png");
                    }
                }
            });
            c.setFileFilter(pngTypeFilter);
            int result = c.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                filename = c.getSelectedFile().getAbsolutePath();
                appService.exportImage(filename);
            }

        });
    }

    private JFileChooser openChooser() {
        return new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }

    private int customThicknessDialog() {
        String num = JOptionPane.showInputDialog(this, "Enter your desired line thickness (number only):", "Text Input", JOptionPane.PLAIN_MESSAGE);
        return Integer.parseInt(num);
    }

    private String showTextInputDialog() {
        return JOptionPane.showInputDialog(this, "Enter text:", "Text Input", JOptionPane.PLAIN_MESSAGE);
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

}
