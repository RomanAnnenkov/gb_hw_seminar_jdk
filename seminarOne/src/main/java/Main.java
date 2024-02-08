import client.ChatClientWindow;
import server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw from seminar one jdk");
        ServerWindow serverWindow = new ServerWindow();
        new ChatClientWindow(serverWindow);
        new ChatClientWindow(serverWindow);
    }
}
