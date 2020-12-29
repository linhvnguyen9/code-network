//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tmp;

import control.Final_NumericServer;
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
import model.Student;

public class NumericClientThread_25102018 extends Thread {
    Socket clientSocket;
    Final_NumericServer serverControl;
    DataInputStream is;
    DataOutputStream os;
    ObjectOutputStream ooS;
    int numOperator = 0;
    int operatorCode = -1;
    String operatorType = null;
    int[] intAnswers = null;
    long[] longAnswers = null;
    Answer answer = null;
    Student student = null;
    int intAnswer = 0;
    long longAnswer = 0L;

    public NumericClientThread_25102018(Socket s, Final_NumericServer serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
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

    private void checkResponse(int code) throws IOException {
        switch(code) {
            case 1:
                int uscln = this.is.readInt();
                if (this.USCLN(this.intAnswers[0], this.intAnswers[1]) == uscln) {
                    System.out.println("Ket qua: " + uscln + " Dung");
                    this.answer.updateAnswer(2, uscln, true);
                } else {
                    System.err.println("Ket qua: " + uscln + " Sai");
                    this.answer.updateAnswer(2, uscln, false);
                }
                break;
            case 2:
                int bscnn = this.is.readInt();
                if (this.BSCNN(this.intAnswers[0], this.intAnswers[1]) == bscnn) {
                    System.out.println("Ket qua: " + bscnn + " Dung");
                    this.answer.updateAnswer(3, bscnn, true);
                } else {
                    System.err.println("Ket qua: " + bscnn + " Sai");
                    this.answer.updateAnswer(3, bscnn, false);
                }
                break;
            case 3:
                boolean isRight = true;
                if (this.operatorCode == 1) {
                    this.sortASC(this.intAnswers);
                } else {
                    this.sortDES(this.intAnswers);
                }

                for(int i = 0; i < this.intAnswers.length; ++i) {
                    if (this.is.readInt() != this.intAnswers[i]) {
                        isRight = false;
                        break;
                    }
                }

                if (isRight) {
                    this.answer.updateAnswer(4, -1, true);
                } else {
                    this.answer.updateAnswer(4, -1, false);
                }
        }

        this.serverControl.updateAnswerList(this.answer);
        this.serverControl.updateView(this.student);
    }

    private void sendQuestion(int numOperator) throws IOException {
        this.intAnswers = new int[numOperator];

        for(int i = 0; i < numOperator; ++i) {
            int tmp = (new Random()).nextInt(10) + 100;
            this.os.writeInt(tmp);
            this.intAnswers[i] = tmp;
        }

    }

    public void run() {
        try {
            super.run();
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.ooS = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.initiateStudentAnswer();
            this.operatorCode = (new Random()).nextInt(2) + 1;
            this.os.writeInt(this.operatorCode);
            this.sendQuestion(2);
            this.checkResponse(this.operatorCode);
            this.sendQuestion(2);
            this.checkResponse(this.operatorCode == 1 ? 2 : 1);
            this.numOperator = (new Random()).nextInt(10) + 10;
            this.os.writeInt(this.numOperator);
            this.sendQuestion(this.numOperator);
            this.checkResponse(3);
            this.ooS.writeObject(this.answer);
        } catch (SocketException var31) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var31.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var30) {
                    Logger.getLogger(NumericClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var30);
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
                    Logger.getLogger(NumericClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var29);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var28) {
                    Logger.getLogger(NumericClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var28);
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
                    Logger.getLogger(NumericClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var27);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var26) {
                    Logger.getLogger(NumericClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var26);
                }
            }
        } finally {
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var25) {
                    Logger.getLogger(NumericClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var25);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var24) {
                    Logger.getLogger(NumericClientThread_25102018.class.getName()).log(Level.SEVERE, (String)null, var24);
                }
            }

        }

    }

    private int USCLN(int a, int b) {
        return b == 0 ? a : this.USCLN(b, a % b);
    }

    private long longUSCLN(long a, long b) {
        return b == 0L ? a : this.longUSCLN(b, a % b);
    }

    private int BSCNN(int a, int b) {
        return a * b / this.USCLN(a, b);
    }

    private long longBSCNN(long a, long b) {
        return a * b / this.longBSCNN(a, b);
    }

    private void sortASC(int[] arr) {
        int temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private void sortLongASC(long[] arr) {
        long temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private void sortDES(int[] arr) {
        int temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private void sortLongDES(long[] arr) {
        long temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private boolean compare(Object[] arr, Object[] arr1) {
        if (arr != null && arr1 != null && arr.length == arr1.length) {
            for(int i = 0; i < arr.length; ++i) {
                Object o1 = arr[i];
                Object o2 = arr1[i];
                if (o1 != o2) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private int[] insert(int[] arr, int k) {
        int arrIndex = arr.length - 1;
        int tempIndex = arr.length;
        int[] tempArr = new int[tempIndex + 1];
        boolean inserted = false;

        for(int i = tempIndex; i >= 0; --i) {
            if (arrIndex > -1 && arr[arrIndex] > k) {
                tempArr[i] = arr[arrIndex--];
            } else if (!inserted) {
                tempArr[i] = k;
                inserted = true;
            } else {
                tempArr[i] = arr[arrIndex--];
            }
        }

        return tempArr;
    }
}
