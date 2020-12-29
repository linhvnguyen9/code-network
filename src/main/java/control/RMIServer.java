package control;

import model.ServerConfiguration;
import model.Student;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {
    ArrayList<Student> studentGetDesList = new ArrayList();

    private boolean isExist(String maSV) {
        if (this.studentGetDesList != null) {
            Iterator it = this.studentGetDesList.iterator();

            Student next;
            do {
                if (!it.hasNext()) {
                    return false;
                }

                next = (Student)it.next();
            } while(!next.getMaSV().equalsIgnoreCase(maSV));

            return true;
        } else {
            return false;
        }
    }

    public RMIServer() throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(4444);
        reg.rebind("Ca2_Server", this);
    }

    @Override
    public ServerConfiguration getObjectServerDes(Student student, ServerConfiguration config) throws RemoteException {
        if (this.studentGetDesList == null) {
            this.studentGetDesList = new ArrayList();
        }

        if (student.getMaSV() != null && !student.getMaSV().trim().equalsIgnoreCase("")) {
            if (!this.isExist(student.getMaSV())) {
                this.studentGetDesList.add(student);
            }

            config.setObjectServerPort(11050);
            config.setCode((new Random()).nextInt(2));
            return config;
        } else {
            return null;
        }
    }

    public synchronized ServerConfiguration getStringServerDes(Student student, ServerConfiguration config) throws RemoteException {
        if (this.studentGetDesList == null) {
            this.studentGetDesList = new ArrayList();
        }

        if (student.getMaSV() != null && !student.getMaSV().trim().equalsIgnoreCase("")) {
            if (!this.isExist(student.getMaSV())) {
                this.studentGetDesList.add(student);
            }

            config.setStringServerPort(11001);
            config.setCode((new Random()).nextInt(2));
            return config;
        } else {
            return null;
        }
    }

    public synchronized ServerConfiguration getNumericServerDes(Student student, ServerConfiguration config) throws RemoteException {
        if (this.studentGetDesList == null) {
            this.studentGetDesList = new ArrayList();
        }

        if (student.getMaSV() != null && !student.getMaSV().trim().equalsIgnoreCase("")) {
            if (!this.isExist(student.getMaSV())) {
                this.studentGetDesList.add(student);
            }

            config.setNumericServerPort(11002);
            config.setCode((new Random()).nextInt(2));
            return config;
        } else {
            return null;
        }
    }

    public ArrayList<Student> getStudentGetDesList() {
        return this.studentGetDesList;
    }
}
