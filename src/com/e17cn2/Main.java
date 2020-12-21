package com.e17cn2;

import control.IRMIServer;
import control.IRMIServerImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            IRMIServer server = new IRMIServerImpl();
            String url = "rmi://10.170.77.139:1099/Server";
            Naming.bind(url, server);
            System.out.println("Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
