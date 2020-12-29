package rmiserver_class;

import client.RemoteClient;
import view.ClientList;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

public class RMIServer_Class {
    public RMIServer_Class() {
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException {
        new ClientList();
        new RemoteClient();
    }
}
