package io.github.unawarespecs.rubber;

import io.github.unawarespecs.rubber.component.*;
import io.github.unawarespecs.rubber.component.Splash;

import javax.swing.*;

public class Main {
     public static void main(String[] args) {

         JFrame splashFrame = new JFrame();
         Splash splashPanel = new Splash();
         splashFrame.add(splashPanel);
         splashFrame.setExtendedState(splashFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
         splashFrame.setVisible(true);
         splashFrame.repaint();
        }
    }

