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

public class StringClientThread_25102018 extends Thread {
    Socket clientSocket;
    StringServer serverControl;
    DataInputStream is;
    DataOutputStream os;
    ObjectOutputStream objectStream;
    StringExam stringExam;
    Answer answer = null;
    Student student = null;

    public StringClientThread_25102018(Socket s, StringServer serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
        this.stringExam = new StringExam();
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
        }

    }

    public void run() {
        try {
            super.run();
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.objectStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.initiateStudentAnswer();
            int code = (Integer.valueOf(this.student.getMaSV().charAt(this.student.getMaSV().length() - 1)) + Integer.valueOf(this.student.getMaSV().charAt(this.student.getMaSV().length() - 2)) + Integer.valueOf(this.student.getMaSV().charAt(this.student.getMaSV().length() - 2))) % 2;
            this.os.writeInt(code);
            StringBuilder subStringBuilder;
            int i;
            int countRespond;
            int character;
            StringBuilder longStringBuilder;
            if (code == 0) {
                this.stringExam.stringGenerate(8);
                this.os.writeUTF(this.stringExam.getStringOrg());
                this.checkResponse();
                subStringBuilder = new StringBuilder();

                for(i = 0; i < 3; ++i) {
                    countRespond = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                    subStringBuilder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(countRespond));
                }

                System.out.println("Sub-String: " + subStringBuilder.toString());
                this.os.writeUTF(subStringBuilder.toString());
                longStringBuilder = new StringBuilder();
                countRespond = 0;

                while(true) {
                    if (countRespond >= (new Random()).nextInt(10) + 10) {
                        System.out.println("Long String: " + longStringBuilder.toString());
                        this.os.writeUTF(longStringBuilder.toString());
                        countRespond = this.is.readInt();
                        this.answer.updateAnswer(1, countRespond, countRespond == this.stringExam.countSubstring(subStringBuilder.toString(), longStringBuilder.toString()));
                        break;
                    }

                    character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                    longStringBuilder.append(subStringBuilder.toString());
                    longStringBuilder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
                    ++countRespond;
                }
            } else {
                subStringBuilder = new StringBuilder();

                for(i = 0; i < 3; ++i) {
                    countRespond = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                    subStringBuilder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(countRespond));
                }

                System.out.println("Sub-String: " + subStringBuilder.toString());
                this.os.writeUTF(subStringBuilder.toString());
                longStringBuilder = new StringBuilder();

                for(countRespond = 0; countRespond < (new Random()).nextInt(10) + 10; ++countRespond) {
                    character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                    longStringBuilder.append(subStringBuilder.toString());
                    longStringBuilder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
                }

                System.out.println("Long String: " + longStringBuilder.toString());
                this.os.writeUTF(longStringBuilder.toString());
                countRespond = this.is.readInt();
                this.answer.updateAnswer(1, countRespond, countRespond == this.stringExam.countSubstring(subStringBuilder.toString(), longStringBuilder.toString()));
                this.stringExam.stringGenerate(8);
                this.os.writeUTF(this.stringExam.getStringOrg());
                this.checkResponse();
            }

            this.serverControl.updateAnswerList(this.answer);
            this.serverControl.updateView(this.student);
            this.objectStream.writeObject(this.answer);
        } catch (SocketException var34) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var34.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var33) {
                    Logger.getLogger(StringClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var33);
                }
            }
        } catch (IOException var35) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var35.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var32) {
                    Logger.getLogger(StringClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var32);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var31) {
                    Logger.getLogger(StringClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var31);
                }
            }
        } catch (Exception var36) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var36.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var30) {
                    Logger.getLogger(StringClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var30);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var29) {
                    Logger.getLogger(StringClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var29);
                }
            }
        } finally {
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var28) {
                    Logger.getLogger(StringClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var28);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var27) {
                    Logger.getLogger(StringClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var27);
                }
            }

        }

    }
}
