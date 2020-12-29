package client;

import control.IRemoteClient;
import model.Student;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoteClient extends UnicastRemoteObject implements IRemoteClient {
    ArrayList<Student> studentLists = new ArrayList();

    public static void main(String[] args) {
        try {
            new RemoteClient();
        } catch (RemoteException var2) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, (String)null, var2);
        } catch (MalformedURLException var3) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, (String)null, var3);
        } catch (UnknownHostException var4) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    public RemoteClient() throws RemoteException, MalformedURLException, UnknownHostException {
        Registry reg = LocateRegistry.createRegistry(12345);
        System.out.println("IP " + InetAddress.getLocalHost().getHostAddress());
        Naming.rebind("rmi://127.0.0.1:4444/Ca2_Server", this);
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

    public Student getStudent() throws RemoteException {
        System.out.println("Get new call to getStudent");
        return new Student("B17DCCN123", "Nguyen Trong Khanh", "192.168.1.8.", 0, "Server_05122020", 12345);
    }

    public int getMax(int a, int b) throws RemoteException {
        System.out.println("Get new call to getMax");
        return a > b ? a : b;
    }

    public int getUSCLN(int a, int b) throws RemoteException {
        System.out.println("Get new call to getUSCLN");
        return this.USCLN(a, b);
    }

    public int getBSCNN(int a, int b) throws RemoteException {
        System.out.println("Get new call to getBSCNN");
        return this.BSCNN(a, b);
    }

    public String getReverse(String str) throws RemoteException {
        System.out.println("Get new call to getReverse" + str);
        return this.reverse(str);
    }

    public String getNormalization(String str) throws RemoteException {
        System.out.println("Get new call to getNormalization" + str);
        String str_tmp = this.chuanHoa(str);
        System.out.println("Chuan hoa : " + str_tmp);
        return str_tmp;
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
}
