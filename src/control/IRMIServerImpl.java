package control;

import model.ServerConfiguration;
import model.Student;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IRMIServerImpl extends UnicastRemoteObject implements IRMIServer, Serializable {
    private static final long serialVersionUID = 1L;

    public IRMIServerImpl() throws RemoteException {

    }

    @Override
    public ServerConfiguration getObjectServerDes(Student student, ServerConfiguration config) {
        System.out.println("getObjectServerDes() student" + student);
        System.out.println("getObjectServerDes() serverConfig" + config);

        return new ServerConfiguration(0, 0, 9999, 1);
    }

    @Override
    public ServerConfiguration getStringServerDes(Student student, ServerConfiguration config) {
        System.out.println("getStringServerDes() student" + student);
        System.out.println("getStringServerDes() serverConfig" + config);

        return new ServerConfiguration(9998, 0, 0, 1);
    }

    @Override
    public ServerConfiguration getNumericServerDes(Student student, ServerConfiguration config) {
        System.out.println("getNumericServerDes() student" + student);
        System.out.println("getNumericServerDes() serverConfig" + config);

        return new ServerConfiguration(0, 9997, 0, 1);
    }
}
