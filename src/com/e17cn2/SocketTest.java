package com.e17cn2;

import model.Student;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 11000);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

    }
}
