package io.github.unawarespecs.rubber.component;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Splash extends JPanel implements MouseListener {
    private BufferedImage image;
    private GPanel gPanel;
    int width;
    int height;
    public Splash() {
        try {
            image = ImageIO.read(new File("proto\\src\\main\\resources\\nette1440_800.png"));
            height = image.getHeight();
            width = image.getWidth();

        } catch (IOException ex) {
            // handle exception...
        }
        setSize(width, height);
        setLayout(null);

        gPanel = new GPanel("GoDraw");
        gPanel.setBounds(1100,700,150,50);
        gPanel.addMouseListener(this);
        this.add(gPanel);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==gPanel){
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            MainFrame mf = new MainFrame();
            mf.setExtendedState(mf.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            mf.setVisible(true);

            topFrame.setVisible(false);;
            this.dispatchEvent(new WindowEvent(topFrame, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
