package tmp;

import control.Final_NumericServer;
import model.Answer;
import model.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NumericClientThread_20102018 extends Thread {
    Socket clientSocket;
    Final_NumericServer serverControl;
    DataInputStream is;
    DataOutputStream os;
    ObjectOutputStream ooS;
    int numOperator = 0;
    int operatorCode = -1;
    String operatorType = null;
    int[] intAnswers = null;
    float[] floatAnswers = null;
    Answer answer = null;
    Student student = null;
    int intAnswer = 0;
    float floatAnswer = 0.0F;

    public NumericClientThread_20102018(Socket s, Final_NumericServer serverControl) {
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

    private void checkResponse() throws IOException {
        int returnOperatorCode = this.is.readInt();
        int i;
        if (this.operatorType.equalsIgnoreCase("java.lang.Integer")) {
            int answer = this.is.readInt();
            switch(returnOperatorCode) {
                case 1:
                    int sum = 0;

                    for(i = 0; i < this.numOperator; ++i) {
                        sum += this.intAnswers[i];
                    }

                    this.intAnswer = sum / this.numOperator;
                    if (this.intAnswer == answer) {
                        System.out.println("Ket qua: " + answer + " Dung");
                        this.answer.updateAnswer(2, answer, true);
                    } else {
                        System.err.println("Ket qua: " + answer + " Sai");
                        this.answer.updateAnswer(2, answer, false);
                    }
                    break;
                case 2:
                    this.intAnswer = this.intAnswers[0];

                    for(i = 1; i < this.numOperator; ++i) {
                        if (this.intAnswer < this.intAnswers[i]) {
                            this.intAnswer = this.intAnswers[i];
                        }
                    }

                    if (this.intAnswer == answer) {
                        System.out.println("Ket qua: " + answer + " Dung");
                        this.answer.updateAnswer(3, answer, true);
                    } else {
                        System.err.println("Ket qua: " + answer + " Sai");
                        this.answer.updateAnswer(3, answer, false);
                    }
                    break;
                case 3:
                    this.intAnswer = this.intAnswers[0];

                    for(i = 1; i < this.numOperator; ++i) {
                        if (this.intAnswer > this.intAnswers[i]) {
                            this.intAnswer = this.intAnswers[i];
                        }
                    }

                    if (this.intAnswer == answer) {
                        System.out.println("Ket qua: " + answer + " Dung");
                        this.answer.updateAnswer(4, answer, true);
                    } else {
                        System.err.println("Ket qua: " + answer + " Sai");
                        this.answer.updateAnswer(4, answer, false);
                    }
            }
        } else {
            float answer = this.is.readFloat();
            switch(this.operatorCode) {
                case 1:
                    float sum = 0.0F;

                    for(i = 0; i < this.numOperator; ++i) {
                        sum += this.floatAnswers[i];
                    }

                    this.floatAnswer = sum / (float)this.numOperator;
                    if ((float)this.intAnswer == answer) {
                        System.out.println("Ket qua: " + answer + " Dung");
                        this.answer.updateAnswer(2, answer, true);
                    } else {
                        System.err.println("Ket qua: " + answer + " Sai");
                        this.answer.updateAnswer(2, answer, false);
                    }
                    break;
                case 2:
                    this.floatAnswer = this.floatAnswers[0];

                    for(i = 1; i < this.numOperator; ++i) {
                        if (this.floatAnswer < this.floatAnswers[i]) {
                            this.floatAnswer = this.floatAnswers[i];
                        }
                    }

                    if ((float)this.intAnswer == answer) {
                        System.out.println("Ket qua: " + answer + " Dung");
                        this.answer.updateAnswer(3, answer, true);
                    } else {
                        System.err.println("Ket qua: " + answer + " Sai");
                        this.answer.updateAnswer(3, answer, false);
                    }
                    break;
                case 3:
                    this.floatAnswer = this.floatAnswers[0];

                    for(i = 1; i < this.numOperator; ++i) {
                        if (this.floatAnswer > this.floatAnswers[i]) {
                            this.floatAnswer = this.floatAnswers[i];
                        }
                    }

                    if ((float)this.intAnswer == answer) {
                        System.out.println("Ket qua: " + answer + " Dung");
                        this.answer.updateAnswer(4, answer, true);
                    } else {
                        System.err.println("Ket qua: " + answer + " Sai");
                        this.answer.updateAnswer(4, answer, false);
                    }
            }
        }

        this.ooS.writeObject(this.answer);
        this.serverControl.updateAnswerList(this.answer);
        this.serverControl.updateView(this.student);
    }

    private void sendQuestion() throws IOException {
        this.numOperator = (new Random()).nextInt(10) + 10;
        this.os.writeInt(this.numOperator);
        if ((new Random()).nextInt(1) == 0) {
            this.operatorType = "java.lang.Integer";
        } else {
            this.operatorType = "java.lang.Float";
        }

        this.os.writeUTF(this.operatorType);
        int i;
        if (this.operatorType.equalsIgnoreCase("java.lang.Integer")) {
            this.intAnswers = new int[this.numOperator];

            for(i = 0; i < this.numOperator; ++i) {
                int tmp = (new Random()).nextInt(10) + 100;
                this.os.writeInt(tmp);
                this.intAnswers[i] = tmp;
            }
        } else {
            this.floatAnswers = new float[this.numOperator];

            for(i = 0; i < this.numOperator; ++i) {
                float tmp = (new Random()).nextFloat() + (float)(new Random()).nextInt(500);
                this.os.writeFloat(tmp);
                this.floatAnswers[i] = tmp;
            }
        }

    }

    public void run() {
        try {
            super.run();
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.ooS = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.initiateStudentAnswer();
            this.sendQuestion();
            this.checkResponse();
            this.checkResponse();
            this.checkResponse();
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
