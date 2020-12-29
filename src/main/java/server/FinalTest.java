package server;

import control.Server;
import view.ClientList;

import java.io.IOException;

public class FinalTest {
    public FinalTest() {
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ClientList view = new ClientList();
        Server multiServer = new Server(view);
        multiServer.openServer();
    }
}
