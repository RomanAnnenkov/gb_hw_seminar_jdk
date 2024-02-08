import client.ChatClientViewSwing;
import server.ServerViewSwing;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw from seminar one jdk");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ServerViewSwing serverViewSwing = new ServerViewSwing();
                new ChatClientViewSwing(serverViewSwing.getServer());
                new ChatClientViewSwing(serverViewSwing.getServer());
            }
        });
    }
}
