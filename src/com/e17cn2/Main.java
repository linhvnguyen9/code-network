package com.e17cn2;

import control.IRMIServer;
import model.Answer;
import model.ServerConfiguration;
import model.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {

    public static void main(String[] args) {
        try {
            IRMIServer server = (IRMIServer) Naming.lookup("rmi://localhost:4444/Ca2_Server");
            System.out.println("Server ready");
            Student student = new Student("B17DCCN380", "Nguyen Van Linh", "localhost", 1);
            ServerConfiguration serverConfiguration = new ServerConfiguration(0, 0, 0, 0);

            serverConfiguration = server.getObjectServerDes(student, serverConfiguration);
            System.out.println(serverConfiguration.getObjectServerPort());
            Socket socket = new Socket("10.170.77.139", serverConfiguration.getObjectServerPort());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(student);
            Answer answer = (Answer) ois.readObject();
            System.out.println(answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
