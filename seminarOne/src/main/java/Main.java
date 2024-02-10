import client.ChatClientViewSwing;
import repository.FileRepository;
import repository.Repository;
import server.ServerViewSwing;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw from seminar one jdk");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Repository<String> repository = new FileRepository("messages.log");
                ServerViewSwing serverViewSwing = new ServerViewSwing(repository);
                new ChatClientViewSwing(serverViewSwing.getServer());
                new ChatClientViewSwing(serverViewSwing.getServer());
            }
        });
    }
}
