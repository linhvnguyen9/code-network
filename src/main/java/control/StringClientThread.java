package control;

import model.Answer;
import model.StringExam;
import model.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    }

    private boolean initiateStudentAnswer() throws IOException {
        this.student = new Student(this.is.readUTF(), this.is.readUTF(), this.clientSocket.getInetAddress().getHostAddress(), this.is.readInt());
        if (this.student.getMaSV().trim().equalsIgnoreCase("")) {
            System.out.println("SV may " + this.student.getIP() + " Nhap khong dung ma sv");
            return false;
        } else {
            this.answer = this.serverControl.getAnswer(this.student);
            if (this.answer == null) {
                System.err.println(this.student.getIP() + " chua qua buoc 1");
                return false;
            } else {
                this.answer.setStudent(this.student);
                return true;
            }
        }
    }

    private void checkResponse() throws IOException {
        System.out.println("GET: " + this.stringExam.getCode());
        switch(this.stringExam.getCode()) {
            case 1:
                this.stringExam.setNumericAnswer(this.is.readInt());
                System.out.println("Ket qua nhan duoc la :" + this.stringExam.getNumericAnswer());
                System.out.println("Ket qua dung  la :" + this.stringExam.getStringOrg().length());
                this.answer.updateAnswer(0, this.stringExam.getNumericAnswer(), this.stringExam.isRightAnswer());
                break;
            case 2:
                this.stringExam.setStringAnswer(this.is.readUTF());
                System.out.println("Ket qua nhan duoc la :" + this.stringExam.getStringAnswer());
                System.out.println("xau goc la :" + this.stringExam.getStringOrg());
                System.out.println("Dap an: " + this.stringExam.isRightAnswer());
                this.answer.updateAnswer(1, this.stringExam.getStringAnswer(), this.stringExam.isRightAnswer());
                break;
            case 3:
                this.stringExam.setStringAnswer(this.is.readUTF());
                System.out.println(this.stringExam.getStringAnswer());
            case 4:
            case 7:
            default:
                break;
            case 5:
                this.stringExam.setNumericAnswer(this.is.readInt());
                System.out.println(this.stringExam.getNumericAnswer());
                break;
            case 6:
                this.stringExam.setNumericAnswer(this.is.readInt());
                this.stringExam.setStringAnswer(this.is.readUTF());
                System.out.println("So ky tu duy nhat nhan duoc la :" + this.stringExam.getNumericAnswer());
                System.out.println("Xau moi là :" + this.stringExam.getStringAnswer());
                System.out.println("xau goc la :" + this.stringExam.getStringOrg());
                this.answer.updateAnswer(0, this.stringExam.getStringAnswer(), this.stringExam.isRightAnswer());
                System.out.println(this.stringExam.getNumericAnswer());
                break;
            case 8:
                this.stringExam.setStringAnswer(this.is.readUTF());
                System.out.println("Xau chuan hoa la :" + this.stringExam.getStringAnswer());
                System.out.println("xau goc la :" + this.stringExam.getStringOrg());
                this.answer.updateAnswer(0, this.stringExam.getStringAnswer(), this.stringExam.isRightAnswer());
                break;
            case 9:
                this.stringExam.setNumericAnswer(this.is.readInt());
                this.stringExam.setStringAnswer(this.is.readUTF());
                this.answer.updateAnswer(0, this.stringExam.getStringAnswer(), this.stringExam.isRightAnswer());
                break;
            case 10:
                String str = this.is.readUTF();
                this.stringExam.setStringAnswer(str);
                String[] strs = this.stringExam.getStringOrg().trim().split(" ");
                String strAnswer = "";

                for (String word : strs) {
                    for (int j = 0; j < word.length(); ++j) {
                        strAnswer = strAnswer + (char) (word.charAt(j) + Integer.parseInt(this.student.getMaSV().trim().substring(this.student.getMaSV().trim().length() - 1)));
                    }

                    strAnswer = strAnswer + " ";
                }

                strAnswer = strAnswer.trim();
                this.answer.updateAnswer(0, this.stringExam.getStringAnswer(), strAnswer.equals(this.stringExam.getStringAnswer()));
                System.out.println(answer.toString());
            case 11:
                this.stringExam.setCharAnswer(this.is.readChar());
                this.answer.updateAnswer(1, this.stringExam.getCharAnswer(), this.stringExam.isRightAnswer());

            case 12:
                String str1 = this.is.readUTF();
                this.stringExam.setStringAnswer(str1);
                this.answer.updateAnswer(2, this.stringExam.getStringAnswer(), this.stringExam.equals(this.substring("this.stringExam 123", 1, 6)));
        }

    }

    public String substring(String str, int start, int end){
        return str.substring(start, end);
    }

    public void run() {
        try {
            super.run();
            this.objectStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            if (this.initiateStudentAnswer()) {
//                int code = (new Random()).nextInt(2);
                int code = this.is.readInt();
                if (code == 1) {
                    //second char
                    this.stringExam.stringGenerate(11);
                    this.os.writeUTF(this.stringExam.getStringOrg());
                    this.checkResponse();

                    //substring
                    this.os.writeUTF("this.stringExam 123");
                    this.os.writeInt(1);
                    this.os.writeInt(6);
                    this.checkResponse();

                    //ceasar
                    this.stringExam.stringGenerate(10);
                    this.os.writeUTF(this.stringExam.getStringOrg());
                    this.os.writeInt(Integer.parseInt(this.student.getMaSV().trim().substring(this.student.getMaSV().trim().length() - 1)));
                    this.checkResponse();
                } else if (code == 0) {
                    //ceasar
                    this.stringExam.stringGenerate(10);
                    this.os.writeUTF(this.stringExam.getStringOrg());
                    this.os.writeInt(Integer.parseInt(this.student.getMaSV().trim().substring(this.student.getMaSV().trim().length() - 1)));
                    this.checkResponse();

                    //substring
                    this.os.writeUTF("this.stringExam 123");
                    this.os.writeInt(1);
                    this.os.writeInt(6);
                    this.checkResponse();

                    //second char
                    this.stringExam.stringGenerate(11);
                    this.os.writeUTF(this.stringExam.getStringOrg());
                    this.checkResponse();
                }

                this.objectStream.writeObject(this.answer);
                this.serverControl.updateAnswerList(this.answer);
                this.serverControl.updateView(this.student);

                return;
            }
        } catch (SocketException var33) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var33.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var32) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var32);
                }

                return;
            }

            return;
        } catch (IOException var34) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var34.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var31) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var31);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var30) {
                    Logger.getLogger(StringClientThread.class.getName()).log(Level.SEVERE, (String)null, var30);
                }

                return;
            }

            return;
        } catch (Exception var35) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var35.printStackTrace();
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

                return;
            }

            return;
        } finally {
            System.out.println(" finally Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
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

        }

    }
}
