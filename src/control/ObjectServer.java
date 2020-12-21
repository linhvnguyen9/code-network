package control;

import model.Answer;
import model.Student;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer implements Runnable {
    private final ServerSocket socket;
    public ObjectServer(int port) throws IOException {
        socket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket clientSocket = socket.accept();

                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

                Student student = (Student) ois.readObject();
                System.out.println("ObjectServer student " + student);

                Answer answer = new Answer(student, new Object[]{}, new boolean[]{}, false);
                oos.writeObject(answer);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
