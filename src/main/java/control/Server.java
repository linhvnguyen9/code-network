package control;

import model.Answer;
import model.Student;
import view.ClientList;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
    InformationServer informationServer = null;
    IRemoteClient rmiServer = null;
    Student input;
    ClientList view;
    ArrayList<Student> svList = new ArrayList();
    ArrayList<Answer> answerList = new ArrayList();

    public Server(ClientList v) {
        this.view = v;
        this.view.setVisible(true);
    }

    public void openServer() throws RemoteException {
        this.informationServer = new InformationServer(this);
        this.informationServer.start();
    }

    public synchronized void addStudent(Student s) {
        if (this.svList == null) {
            this.svList = new ArrayList();
            this.svList.add(s);
        } else {
            Iterator<Student> it = this.svList.iterator();
            boolean isNew = true;

            while(it.hasNext()) {
                Student next = (Student)it.next();
                if (s.getMaSV() != null && next.getMaSV() != null && next.getMaSV().equalsIgnoreCase(s.getMaSV())) {
                    isNew = false;
                    break;
                }
            }

            if (isNew) {
                this.svList.add(s);
            }
        }

    }

    public Student getStudent(String maSV) {
        Iterator it = this.svList.iterator();

        Student next;
        do {
            if (!it.hasNext()) {
                return null;
            }

            next = (Student)it.next();
        } while(!next.getMaSV().equalsIgnoreCase(maSV));

        return next;
    }

    public Answer getAnswer(Student student) {
        Iterator it = this.answerList.iterator();

        Answer next;
        do {
            if (!it.hasNext()) {
                return null;
            }

            next = (Answer)it.next();
        } while(!next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV()));

        return next;
    }

    public synchronized void updateAnswerList(Answer answer) {
        Iterator<Answer> it = this.answerList.iterator();
        boolean isUpdate = false;

        while(it.hasNext()) {
            Answer next = (Answer)it.next();
            if (next.getStudent().getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
                isUpdate = true;
            }
        }

        if (!isUpdate) {
            this.answerList.add(answer);
        }

    }

    public synchronized void updateView(Student student) {
        Iterator it = this.answerList.iterator();

        while(it.hasNext()) {
            Answer next = (Answer)it.next();
            if (next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV())) {
                this.view.updateAnswerView(next);
                break;
            }
        }

    }

    public static void main(String[] args) {
    }
}
