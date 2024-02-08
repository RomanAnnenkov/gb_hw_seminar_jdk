package server;

import client.ChatClientWindow;
import exceptions.LoginTakenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton buttonStart = new JButton("start");
    private final JButton buttonStop = new JButton("stop");
    private final JTextArea informationMessages = new JTextArea();
    private JTextArea storedUserMessages;
    private File messagesFile;
    private boolean isServerWorking;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ");

    private final Map<String, ChatClientWindow> authorizedUsers;

    public ServerWindow() {
        this.authorizedUsers = new HashMap<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat server");

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel.add(buttonStart);
        buttonsPanel.add(buttonStop);
        add(buttonsPanel, BorderLayout.SOUTH);
        informationMessages.setEditable(false);
        add(new JScrollPane(informationMessages), BorderLayout.CENTER);


        setVisible(true);

        isServerWorking = false;

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationMessages.append(LocalDateTime.now().format(dateTimeFormatter));
                if (isServerWorking) {
                    informationMessages.append("server already working\n");
                } else {
                    isServerWorking = true;
                    storedUserMessages = initMessages();
                    informationMessages.append("server started\n");
                }
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    saveMessages(storedUserMessages);
                    disconnectUsers();
                    informationMessages.append(LocalDateTime.now().format(dateTimeFormatter) + "server stopped\n");
                } else {
                    informationMessages.append(LocalDateTime.now().format(dateTimeFormatter) + "server not working\n");
                }
            }
        });

    }

    public JTextArea getMessages() {
        return storedUserMessages;
    }

    private JTextArea initMessages() {
        JTextArea messages = new JTextArea();
        messagesFile = new File("messages.log");
        if (messagesFile.exists() && messagesFile.isFile()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(messagesFile))) {
                while (bufferedReader.ready()) {
                    messages.append(bufferedReader.readLine());
                    messages.append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return messages;
    }

    private void saveMessages(JTextArea userMessages) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(messagesFile))) {
            writer.write(userMessages.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authorize(ChatClientWindow clientWindow, String login, int passwordHash) throws LoginTakenException {
        if (isServerWorking) {
            if (authorizedUsers.containsKey(login)) {
                throw new LoginTakenException(login + " is already taken, try another name.");
            }
            authorizedUsers.put(login, clientWindow);
            informationMessages.append(LocalDateTime.now().format(dateTimeFormatter) + login + " connected\n");
            return true;
        }
        return false;
    }

    public void receiveMessage(String login, String message) {
        if (authorizedUsers.containsKey(login)) {
            updateMessages(LocalDateTime.now().format(dateTimeFormatter) + login + ": " + message + "\n");
        }
    }

    private void updateMessages(String message) {
        informationMessages.append(message);
        storedUserMessages.append(message);
        for (ChatClientWindow client : authorizedUsers.values()) {
            client.newMessageFromServer(message);
        }
    }

    private void disconnectUsers() {
        for (String clientName : authorizedUsers.keySet()) {
            authorizedUsers.get(clientName).disconnect();
            informationMessages.append(LocalDateTime.now().format(dateTimeFormatter) + clientName + " disconnected from server.\n");
        }
        authorizedUsers.clear();
    }

}
