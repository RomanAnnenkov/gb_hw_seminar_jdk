package server;

import client.ChatClient;
import exceptions.LoginTakenException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ");
    private final Map<String, ChatClient> authorizedUsers;
    private boolean isServerWorking;
    private final ServerView view;
    private File historyFile;

    public ChatServer(ServerView view) {
        authorizedUsers = new HashMap<>();
        this.view = view;
        isServerWorking = false;
    }

    public boolean getServerStatus() {
        return isServerWorking;
    }

    public void setServerStatus(boolean status) {
        isServerWorking = status;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public boolean authorize(ChatClient client, String login, int passwordHash) throws LoginTakenException {
        if (isServerWorking) {
            if (authorizedUsers.containsKey(login)) {
                throw new LoginTakenException(login + " is already taken, try another name.");
            }
            authorizedUsers.put(login, client);
            view.showInfoMessage(LocalDateTime.now().format(dateTimeFormatter) + login + " connected\n");
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
        view.showInfoMessage(message);
        saveMessage(message);
        for (ChatClient client : authorizedUsers.values()) {
            client.receiveMessageFromServer(message);
        }
    }

    void disconnectUsers() {
        for (String clientName : authorizedUsers.keySet()) {
            authorizedUsers.get(clientName).disconnect();
            view.showInfoMessage(LocalDateTime.now().format(dateTimeFormatter) + clientName + " disconnected from server.\n");
        }
        authorizedUsers.clear();
    }

    public String loadMessages() {
        StringBuilder builder = new StringBuilder();
        historyFile = new File("messages.log");
        if (historyFile.exists() && historyFile.isFile()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(historyFile))) {
                while (bufferedReader.ready()) {
                    builder.append(bufferedReader.readLine());
                    builder.append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return builder.toString();
    }

    private void saveMessage(String userMessages) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true))) {
            writer.write(userMessages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
