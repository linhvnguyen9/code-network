package server;

import control.Server;
import view.ClientList;

import java.rmi.RemoteException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws RemoteException {
        ClientList view = new ClientList();
        Server multiServer = new Server(view);
        multiServer.openServer();
    }
}
