//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tmp;

import control.ObjectServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

public class ObjectClientThread extends Thread {
    Socket clientSocket;
    ObjectServer serverControl;
    ObjectInputStream is;
    ObjectOutputStream os;
    Answer answer = null;
    Student student = null;

    public ObjectClientThread(Socket s, ObjectServer serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
    }

    public void initiateStudentAnswer() {
        try {
            this.student = (Student)this.is.readObject();
            if (this.student.getMaSV() == null) {
                return;
            }

            this.serverControl.addStudent(this.student);
            this.answer = this.serverControl.getAnswer(this.student);
            if (this.answer == null) {
                System.out.println("Got a new registration, initiate answer");
                this.answer = new Answer(this.student);
                this.answer.setAlreadyRegistration(true);
            } else {
                this.answer.setStudent(this.student);
                this.answer.setAlreadyRegistration(true);
            }

            this.serverControl.updateAnswerList(this.answer);
            this.serverControl.updateView(this.student);
        } catch (IOException var7) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var7.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var6) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var6);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var5) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var5);
                }
            }
        } catch (ClassNotFoundException var8) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var8.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var4) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var8);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var3) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var8);
                }
            }
        }

    }

    public void run() {
        super.run();

        try {
            this.is = new ObjectInputStream(this.clientSocket.getInputStream());
            this.os = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.initiateStudentAnswer();
            this.os.writeObject(this.answer);
        } catch (SocketException var31) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var31.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var30) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var30);
                }
            }
        } catch (IOException var32) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var32.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var29) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var29);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var28) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var28);
                }
            }
        } catch (Exception var33) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var33.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var27) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var27);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var26) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var26);
                }
            }
        } finally {
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var25) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var25);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var24) {
                    Logger.getLogger(NumericClientThread_20102018.class.getName()).log(Level.SEVERE, (String)null, var24);
                }
            }

        }

    }
}
