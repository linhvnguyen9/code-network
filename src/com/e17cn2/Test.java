package com.e17cn2;

import control.IRMIServer;
import model.ServerConfiguration;
import model.Student;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Test {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IRMIServer server = (IRMIServer) Naming.lookup("rmi://10.170.77.139:1099/Server");
        ServerConfiguration config1 = server.getObjectServerDes(new Student("B17DCCN380", "Nguyen Van Linh", "1", 1), new ServerConfiguration(0, 0, 0, 0));
        System.out.println(config1);
    }
}
