package control;

import model.Answer;
import model.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformationServerThread extends Thread {
    Socket clientSocket;
    InformationServer serverControl;
    DataInputStream is;
    DataOutputStream os;
    ObjectOutputStream objectStream;
    IRemoteClient rmiObject;
    Answer answer = null;
    Student student = null;

    public InformationServerThread(Socket s, InformationServer serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
    }

    private boolean initiateStudentAnswer() throws IOException {
        this.student = new Student(this.is.readUTF(), this.is.readUTF(), this.is.readUTF(), this.is.readInt(), this.is.readUTF(), this.is.readInt());
        if (this.student.getMaSV().trim().equalsIgnoreCase("")) {
            System.out.println("SV may " + this.student.getIP() + " Nhap khong dung ma sv");
            return false;
        } else {
            this.answer = this.serverControl.getAnswer(this.student);
            if (this.answer == null) {
                this.answer = new Answer();
            }

            this.answer.setStudent(this.student);
            return true;
        }
    }

    public void run() {
        try {
            super.run();
            this.objectStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            this.initiateStudentAnswer();

            try {
                Registry reg = LocateRegistry.getRegistry(this.student.getIP(), this.student.getRmiPort());
                System.out.println("rmi://" + this.student.getIP() + ":" + this.student.getRmiPort() + "/" + this.student.getRmiName());
                this.rmiObject = (IRemoteClient) Naming.lookup("rmi://" + this.student.getIP() + ":" + this.student.getRmiPort() + "/" + this.student.getRmiName());
                Student tmpStudent = this.rmiObject.getStudent();
                if (!tmpStudent.getHovaten().equalsIgnoreCase(this.student.getHovaten()) && !tmpStudent.getMaSV().equalsIgnoreCase(this.student.getMaSV())) {
                    System.out.println(this.student.getHovaten() + "Chua dang ky");
                } else {
                    this.answer.setIsAlreadyRegistration(true);
                    this.serverControl.updateAnswerList(this.answer);
                    this.serverControl.updateView(this.student);
                }

                int a = (new Random()).nextInt(50);
                int b = (new Random()).nextInt(100);
                int max;
                if (a > b) {
                    max = a;
                } else {
                    max = b;
                }

                if (max == this.rmiObject.getMax(a, b)) {
                    this.answer.setIsMaxNumericAnswerRight(true);
                } else {
                    this.answer.setIsMaxNumericAnswerRight(false);
                }

                this.serverControl.updateAnswerList(this.answer);
                this.serverControl.updateView(this.student);
                int uscln = this.USCLN(a, b);
                if (uscln == this.rmiObject.getUSCLN(a, b)) {
                    this.answer.setIsUSCLNNumericAnswerRight(true);
                } else {
                    this.answer.setIsUSCLNNumericAnswerRight(false);
                }

                int bscnn = this.BSCNN(a, b);
                if (bscnn == this.rmiObject.getBSCNN(a, b)) {
                    this.answer.setIsBSCNNNumericAnswerRight(true);
                } else {
                    this.answer.setIsBSCNNNumericAnswerRight(false);
                }

                this.serverControl.updateAnswerList(this.answer);
                this.serverControl.updateView(this.student);
                String orgininStr = this.randomAlphaString(20);
                if (this.reverse(orgininStr).equalsIgnoreCase(this.rmiObject.getReverse(orgininStr))) {
                    this.answer.setIsReverseStringAnswerRight(true);
                } else {
                    this.answer.setIsReverseStringAnswerRight(false);
                }

                this.serverControl.updateAnswerList(this.answer);
                this.serverControl.updateView(this.student);
                String normalizeStr = this.randomAlphaSpaceString((new Random()).nextInt(10) + 10);
                if (this.chuanHoa(normalizeStr).equalsIgnoreCase(this.rmiObject.getNormalization(normalizeStr))) {
                    this.answer.setIsNormalizationStringAnswerRight(true);
                } else {
                    this.answer.setIsNormalizationStringAnswerRight(false);
                }

                this.serverControl.updateAnswerList(this.answer);
                this.serverControl.updateView(this.student);
            } catch (RemoteException var39) {
                System.out.println(" ------- Loi RMI ------- ");
                System.out.println("--" + this.student.getHovaten() + ", IP: " + this.student.getIP() + ", Name: " + this.student.getRmiName() + ", Port: " + this.student.getRmiPort());
                var39.printStackTrace();
            }
        } catch (SocketException var40) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var40.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var38) {
                    Logger.getLogger(InformationServerThread.class.getName()).log(Level.SEVERE, (String)null, var38);
                }
            }
        } catch (IOException var41) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var41.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var37) {
                    Logger.getLogger(InformationServerThread.class.getName()).log(Level.SEVERE, (String)null, var37);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var36) {
                    Logger.getLogger(InformationServerThread.class.getName()).log(Level.SEVERE, (String)null, var36);
                }
            }
        } catch (Exception var42) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var42.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var35) {
                    Logger.getLogger(InformationServerThread.class.getName()).log(Level.SEVERE, (String)null, var35);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var34) {
                    Logger.getLogger(InformationServerThread.class.getName()).log(Level.SEVERE, (String)null, var34);
                }
            }
        } finally {
            System.out.println(" finally Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var33) {
                    Logger.getLogger(InformationServerThread.class.getName()).log(Level.SEVERE, (String)null, var33);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var32) {
                    Logger.getLogger(InformationServerThread.class.getName()).log(Level.SEVERE, (String)null, var32);
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

    public String chuanHoa(String strInput) {
        String strOutput = "";
        StringTokenizer strToken = new StringTokenizer(strInput, " ,\t,\r");

        for(strOutput = strOutput + "" + this.chuyenInHoa(strToken.nextToken()); strToken.hasMoreTokens(); strOutput = strOutput + " " + this.chuyenInHoa(strToken.nextToken())) {
        }

        return strOutput;
    }

    public String chuyenInHoa(String str) {
        String s = str.substring(0, 1);
        String strOutput = str.replaceFirst(s, s.toUpperCase());
        return strOutput;
    }

    public String reverse(String str) {
        String tmp = "";

        for(int i = str.length() - 1; i >= 0; --i) {
            tmp = tmp + str.substring(i, i + 1);
        }

        return tmp;
    }

    private String randomAlphaString(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            int character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
            builder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
        }

        return builder.toString();
    }

    private String randomAlphaSpaceString(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            for(int i = 0; i < (new Random()).nextInt(7) + 1; ++i) {
                int character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                builder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
            }

            if (count != 0) {
                builder.append(" ");
            }
        }

        return builder.toString().trim();
    }
}
