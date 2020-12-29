package tmp;

import control.Server;
import model.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread {
    Socket clientSocket;
    Server serverControl;
    ObjectInputStream is;
    ObjectOutputStream os;

    public ClientThread(Socket s, Server serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
    }

    public void run() {
        try {
            super.run();
            this.is = new ObjectInputStream(this.clientSocket.getInputStream());
            this.os = new ObjectOutputStream(this.clientSocket.getOutputStream());
            Object o = this.is.readObject();
            if (o instanceof Student) {
                Student input = (Student)o;
                if (this.clientSocket.getInetAddress().getHostAddress() != null && this.clientSocket.getInetAddress() != null && this.clientSocket != null) {
                    input.setIP(this.clientSocket.getInetAddress().getHostAddress());
                } else {
                    input.setIP("NaN");
                }

                this.serverControl.addStudent(input);
                this.is.close();
                this.os.close();
                this.clientSocket.close();
            } else {
                System.out.println("O is not a instance of Student" + o.getClass());
                this.is.close();
                this.os.close();
                this.clientSocket.close();
            }
        } catch (SocketException var38) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var38.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var37) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var37);
                }
            }
        } catch (IOException var39) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var39.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var36) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var36);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var35) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var35);
                }
            }
        } catch (ClassNotFoundException var40) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var40.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var34) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var34);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var33) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var33);
                }
            }
        } catch (Exception var41) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var41.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var32) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var32);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var31) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var31);
                }
            }
        } finally {
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var30) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var30);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var29) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, (String)null, var29);
                }
            }

        }

    }
}
