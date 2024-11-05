package io.github.unawarespecs.rubber.component;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.CommandService;

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

public class DrawingMenuBar extends JMenuBar implements ActionListener {

    private AppService appService;

    private JMenuItem openMenuItem = new JMenuItem("Open");
    private JMenuItem saveMenuItem = new JMenuItem("Save");
    private JMenuItem saveAsMenuItem = new JMenuItem("Save as");
    private JMenuItem closeMenuItem = new JMenuItem("Close");
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
    private JMenuItem selectMenuItem = new JMenuItem("Select");

    public DrawingMenuBar(AppService appService){
        super();
        this.appService  = appService;

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

        this.add(fileMenu);
        fileMenu.add(openMenuItem);
        openMenuItem.setMnemonic(KeyEvent.VK_O);
        fileMenu.add(saveMenuItem);
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        fileMenu.add(saveAsMenuItem);
        saveAsMenuItem.setMnemonic(KeyEvent.VK_A);
        fileMenu.add(closeMenuItem);
        closeMenuItem.setMnemonic(KeyEvent.VK_C);
        fileMenu.add(exitMenuItem);
        exitMenuItem.setMnemonic(KeyEvent.VK_X);

        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        saveAsMenuItem.addActionListener(this);
        closeMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        editMenu.add(undoMenuItem);
        KeyStroke keyStrokeToUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
        undoMenuItem.setAccelerator(keyStrokeToUndo);
        editMenu.add(redoMenuItem);
        KeyStroke keyStrokeToRedo = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
        redoMenuItem.setAccelerator(keyStrokeToRedo);

        this.add(editMenu);
        undoMenuItem.addActionListener(this);
        redoMenuItem.addActionListener(this);

        this.add(viewMenu);
        this.add(drawMenu);
        this.add(attributeMenu);

        attributeMenu.add(colorMenu);
        colorMenu.add(foreColorMenuItem);
        colorMenu.add(fillColorMenuItem);
        foreColorMenuItem.addActionListener(this);
        fillColorMenuItem.addActionListener(this);

        attributeMenu.add(thicknessLineMenu);

        thicknessLineMenu.add(thinLineMenuItem);
        thicknessLineMenu.add(mediumLineMenuItem);
        thicknessLineMenu.add(thickLineMenuItem);

        thinLineMenuItem.addActionListener(this);
        mediumLineMenuItem.addActionListener(this);
        thickLineMenuItem.addActionListener(this);
        drawMenu.add(ellipseMenuItem);
        ellipseMenuItem.addActionListener(this);

        drawMenu.add(lineMenuItem);
        lineMenuItem.addActionListener(this);

        drawMenu.add(rectangleMenuItem);
        rectangleMenuItem.addActionListener(this);
        drawMenu.add(curveMenuItem);
        curveMenuItem.addActionListener(this);
        drawMenu.add(selectMenuItem);
        selectMenuItem.addActionListener(this);
    }

    @Getter
    @Setter
    private ShapeMode shapeMode = ShapeMode.Line;

    @Getter
    @Setter
    BasicStroke basicStroke = new BasicStroke(10);

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == lineMenuItem){
            appService.setShapeMode( ShapeMode.Line);
        }
        else if(e.getSource() == ellipseMenuItem){
            appService.setShapeMode( ShapeMode.Ellipse);
        }
        else if(e.getSource() == rectangleMenuItem){
            appService.setShapeMode( ShapeMode.Rectangle);
        }
        else if(e.getSource() == selectMenuItem){
            appService.setShapeMode( ShapeMode.Select);
        }
        else if(e.getSource() == thinLineMenuItem){
            appService.setLineThickness(1);
        }
        else if(e.getSource() == mediumLineMenuItem){
            appService.setLineThickness(5);
        }
        else if(e.getSource() == thickLineMenuItem){
            appService.setLineThickness(10);
        }
        else if(e.getSource() == foreColorMenuItem){
            Color color = JColorChooser.showDialog(null, "Choose a color", appService.getColor());
            appService.setColor(color);
        }
        else if(e.getSource() == fillColorMenuItem){
            Color color = JColorChooser.showDialog(null, "Choose a color", appService.getColor());
            appService.setFillColor(color);
        }
        else if(e.getSource() == undoMenuItem){
            appService.undo();
        }
        else if(e.getSource() == redoMenuItem){
            CommandService.redo();
        }
        else if(e.getSource() == saveAsMenuItem){
            String filename = appService.getFileName();
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
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
            }
            appService.save();
        }
        else if(e.getSource() == saveMenuItem){
            String filename = appService.getFileName();
            if(filename == null) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
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
                }
            }
            appService.save();
        }
        else if(e.getSource() == openMenuItem) {
            String filename = appService.getFileName();
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
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
        }else if(e.getSource() == closeMenuItem){
            appService.close();
        }else if(e.getSource() ==exitMenuItem){
            appService.exit();
        }
    }
}
