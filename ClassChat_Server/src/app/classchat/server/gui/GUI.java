package app.classchat.server.gui;

import app.classchat.server.server.ChatServer;
import app.classchat.server.utils.logger.Logger;
import app.classchat.server.utils.logger.LoggerUsage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static app.classchat.server.utils.Reference.isServerRunning;

public class GUI {

    private static final Logger logger = new Logger("ClassChat GUI", LoggerUsage.ALL_USAGES);

    private static JFrame frame = new JFrame("ClassChat Server - By GameMaster");

    public static void launchGUI() {


        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        logger.log("Initializing ClassChat GUI...");

        ImageIcon icon = new ImageIcon("resources/icon.png");
        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JLabel header = new JLabel();
        header.setText("ClassChat Server v2.0");
        header.setIcon(scaledIcon);
        header.setHorizontalTextPosition(JLabel.CENTER);
        header.setVerticalTextPosition(JLabel.BOTTOM);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Roboto Regular", Font.BOLD, 50));
        header.setVerticalAlignment(JLabel.TOP);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField ip_field = new JTextField();
        ip_field.setPreferredSize(new Dimension(200, 30));
        ip_field.setMaximumSize(new Dimension(200, 30));
        ip_field.setFont(new Font("Roboto Regular", Font.BOLD, 25));
        ip_field.setBackground(Color.BLACK);
        ip_field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ip_field.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }

            @Override
            public void focusLost(FocusEvent e) {
                ip_field.setBorder(null);
            }
        });
        ip_field.setForeground(new Color(0x00FF00));
        ip_field.setCaretColor(Color.WHITE);
        ip_field.setText("127.0.0.1");
        ip_field.setAlignmentX(Component.CENTER_ALIGNMENT);
        ip_field.setEnabled(false);

        JButton start_server = new JButton();
        start_server.setBounds(100, 100, 250, 100);
        start_server.setText("Start Server");
        start_server.setPreferredSize(new Dimension(50, 40));
        start_server.setFocusable(false);
        start_server.setHorizontalTextPosition(JButton.CENTER);
        start_server.setVerticalTextPosition(JButton.BOTTOM);
        start_server.setFont(new Font("Roboto Regular", Font.BOLD, 30));
        start_server.setForeground(Color.GREEN);
        start_server.setBackground(Color.darkGray);
        start_server.setBorder(BorderFactory.createEtchedBorder());
        start_server.setAlignmentX(Component.CENTER_ALIGNMENT);
        start_server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerRunning) {
                    ChatServer.stopServer();
                    start_server.setText("Start Server");
                    start_server.setForeground(Color.GREEN);
                    isServerRunning = false;

                } else {
                    destroyGUI();
                    ChatServer.launchServer();
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(1200, 700);
        frame.setVisible(true);

        logger.log("Initialized ClassChat GUI");

        panel.add(header);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(ip_field);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(start_server);
        panel.setBackground(Color.BLACK);
        frame.setIconImage(icon.getImage());

    }

    public static void destroyGUI() {

        logger.warn("GUI was been destroyed.");
        frame.dispose();

    }

}
