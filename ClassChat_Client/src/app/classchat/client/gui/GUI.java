package app.classchat.client.gui;

import app.classchat.client.client.ChatClient;
import app.classchat.client.logger.Logger;
import app.classchat.client.logger.LoggerUsage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI {

    public static String server_ip;
    public static String username;
    private static JFrame frame = new JFrame("ClassChat Client - By GameMaster");
    private static final Logger logger = new Logger("ClassChat Client GUI", LoggerUsage.ALL_USAGES);

    public static void launchGUI() {

        logger.log("Initialisation de ClassChat Client GUI...");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel header = new JLabel();
        header.setText("ClassChat Client v2.0");
        header.setHorizontalTextPosition(JLabel.CENTER);
        header.setVerticalTextPosition(JLabel.BOTTOM);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Roboto Regular", Font.BOLD, 50));
        header.setVerticalAlignment(JLabel.CENTER);
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
        ip_field.setText("192.0.0.1");
        ip_field.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField user_field = new JTextField();
        user_field.setPreferredSize(new Dimension(200, 30));
        user_field.setMaximumSize(new Dimension(200, 30));
        user_field.setFont(new Font("Roboto Regular", Font.BOLD, 25));
        user_field.setBackground(Color.BLACK);
        user_field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                user_field.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }

            @Override
            public void focusLost(FocusEvent e) {
                user_field.setBorder(null);
            }
        });
        user_field.setForeground(new Color(0x00FF00));
        user_field.setCaretColor(Color.WHITE);
        user_field.setText("Anonymous");
        user_field.setAlignmentX(Component.CENTER_ALIGNMENT);
        user_field.setEnabled(false);

        JButton start_server = new JButton();
        start_server.setBounds(100, 100, 250, 100);
        start_server.setText("Connection au Serveur");
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

                server_ip = ip_field.getText();
                username = user_field.getText();
                destroyGUI();
                ChatClient.launchClient(server_ip, username);

            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(1200, 700);
        frame.setVisible(true);

        logger.log("ClassChat Client GUI à été initialisé");

        panel.add(header);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(ip_field);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(user_field);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(start_server);
        panel.setBackground(Color.BLACK);

    }

    public static void destroyGUI() {
        logger.warn("Le GUI à été détruit");
        frame.dispose();
    }

}
