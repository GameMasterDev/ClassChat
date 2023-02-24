package com.gamemaster.discordbotmaker.gui;

import javax.swing.*;
import java.awt.*;

public class Menu {

    // Frame and Panel
    private static JFrame frame;
    private static JPanel panel;

    public static void display() {

        panel = (JPanel)frame.getContentPane();
        panel.setLayout((LayoutManager) null);
        frame.setSize(500, 500);
        start();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo((Component) null);

    }

    public static void exit() {
        frame.dispose();
    }

    private static void start() {

        // Logo
        String logoSRC = "/img/logo200.png";
        ImageIcon logo = new ImageIcon(Menu.class.getResource(logoSRC));
        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(logo);
        logoLabel.setBounds(150, 10, 200, 200);
        panel.add(logoLabel);

        // Frame Logo
        frame.setIconImage(logo.getImage());

    }

}
