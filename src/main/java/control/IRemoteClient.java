package control;

import model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteClient extends Remote {
    Student getStudent() throws RemoteException;

    int getMax(int var1, int var2) throws RemoteException;

    int getUSCLN(int var1, int var2) throws RemoteException;

    int getBSCNN(int var1, int var2) throws RemoteException;

    String getReverse(String var1) throws RemoteException;

    String getNormalization(String var1) throws RemoteException;
}
