//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tmp;

import control.StringServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.StringExam;
import model.Student;

public class StringClientThread extends Thread {
    Socket clientSocket;
    StringServer serverControl;
    DataInputStream is;
    DataOutputStream os;
    ObjectOutputStream objectStream;
    StringExam stringExam;
    Answer answer = null;
    Student student = null;

    public StringClientThread(Socket s, StringServer serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
        this.stringExam = new StringExam();
        this.stringExam.stringGenerate((new Random()).nextInt(2) + 1);
    }

    private void initiateStudentAnswer() throws IOException {
        this.student = new Student(this.is.readUTF(), this.is.readUTF(), this.clientSocket.getInetAddress().getHostAddress(), this.is.readInt());
        this.answer = this.serverControl.getAnswer(this.student);
        if (this.answer == null) {
            System.out.println("Answer is null");
            this.answer = new Answer(this.student);
        } else {
            this.answer.setStudent(this.student);
        }

    }

    private void checkResponse() throws IOException {
        switch(this.stringExam.getCode()) {
            case 1:
                this.stringExam.setNumericAnswer(this.is.readInt());
                System.out.println("Ket qua nhan duoc la :" + this.stringExam.getNumericAnswer());
                System.out.println("Ket qua dung  la :" + this.stringExam.getStringOrg().length());
                if (this.stringExam.getNumericAnswer() == this.stringExam.getStringOrg().length()) {
                    this.os.writeUTF("Dap an Dung");
                } else {
                    this.os.writeUTF("Dap an chua dung");
                }

                this.answer.updateAnswer(0, this.stringExam.getNumericAnswer(), this.stringExam.isRightAnswer());
                break;
            case 2:
                this.stringExam.setStringAnswer(this.is.readUTF());
                System.out.println("Ket qua nhan duoc la :" + this.stringExam.getStringAnswer());
                System.out.println("xau goc la :" + this.stringExam.getStringOrg());
                System.out.println("Dap an: " + this.stringExam.isRightAnswer());
                if (this.stringExam.isRightAnswer()) {
                    this.os.writeUTF("Dap an dung");
                } else {
                    this.os.writeUTF("Dap an Sai");
                }

                this.answer.updateAnswer(1, this.stringExam.getStringAnswer(), this.stringExam.isRightAnswer());
                break;
            case 3:
                this.stringExam.setStringAnswer(this.is.readUTF());
                System.out.println(this.stringExam.getStringAnswer());
                break;
            case 4:
                this.stringExam.setNumericAnswer(this.is.readInt());
                System.out.println(this.stringExam.getNumericAnswer());
                break;
            case 5:
                this.stringExam.setNumericAnswer(this.is.readInt());
                System.out.println(this.stringExam.getNumericAnswer());
        }

    }

    public void run() {
        try {
            super.run();
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.initiateStudentAnswer();
            this.os.writeInt(this.stringExam.getCode());
            this.os.writeUTF(this.stringExam.getStringOrg());
            this.checkResponse();
            this.serverControl.updateAnswerList(this.answer);
            this.serverControl.updateView(this.student);
        } catch (SocketException var31) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var31.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var30) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var30);
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
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var29);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var28) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var28);
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
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var27);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var26) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var26);
                }
            }
        } finally {
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var25) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var25);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var24) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var24);
                }
            }

        }

    }
}
