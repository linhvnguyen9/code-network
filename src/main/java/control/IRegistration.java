package control;

import model.Answer;
import model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRegistration extends Remote {
    Student register(Student s) throws RemoteException;
    Answer answer(Answer answer)throws RemoteException;
}
