package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class ServerViewSwing extends JFrame implements ServerView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton buttonStart = new JButton("start");
    private final JButton buttonStop = new JButton("stop");
    private final JTextArea informationMessages = new JTextArea();
    private ChatServer server;

    public ServerViewSwing() {
        server = new ChatServer(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat server");

        addPanels();
        addListeners();

        setVisible(true);
    }

    private void addPanels() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel.add(buttonStart);
        buttonsPanel.add(buttonStop);
        add(buttonsPanel, BorderLayout.SOUTH);
        informationMessages.setEditable(false);
        add(new JScrollPane(informationMessages), BorderLayout.CENTER);
    }

    private void addListeners() {
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationMessages.append(LocalDateTime.now().format(server.getDateTimeFormatter()));
                if (server.getServerStatus()) {
                    informationMessages.append("server already working\n");
                } else {
                    server.setServerStatus(true);
                    informationMessages.append("server started\n");
                }
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getServerStatus()) {
                    server.setServerStatus(false);
                    server.disconnectUsers();
                    informationMessages.append(LocalDateTime.now().format(server.getDateTimeFormatter()) + "server stopped\n");
                } else {
                    informationMessages.append(LocalDateTime.now().format(server.getDateTimeFormatter()) + "server not working\n");
                }
            }
        });
    }

    public ChatServer getServer() {
        return server;
    }


    @Override
    public void showInfoMessage(String message) {
        informationMessages.append(message);
    }
}