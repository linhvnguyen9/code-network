package control;

import model.ServerConfiguration;
import model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIServer  extends Remote {
    ServerConfiguration getObjectServerDes(Student var1, ServerConfiguration var2) throws RemoteException;

    ServerConfiguration getStringServerDes(Student var1, ServerConfiguration var2) throws RemoteException;

    ServerConfiguration getNumericServerDes(Student var1, ServerConfiguration var2) throws RemoteException;
}