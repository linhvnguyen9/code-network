package model;

import java.io.Serializable;
import java.util.Arrays;

public class Answer implements Serializable {
    final long serialVersionUID = 2L;
    Student student;
    Object[] answers;
    boolean[] isRights;
    boolean alreadyRegistration;

    public Answer(Student student, Object[] answers, boolean[] isRights, boolean alreadyRegistration) {
        this.student = student;
        this.answers = answers;
        this.isRights = isRights;
        this.alreadyRegistration = alreadyRegistration;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Object[] getAnswers() {
        return answers;
    }

    public void setAnswers(Object[] answers) {
        this.answers = answers;
    }

    public boolean[] getIsRights() {
        return isRights;
    }

    public void setIsRights(boolean[] isRights) {
        this.isRights = isRights;
    }

    public boolean isAlreadyRegistration() {
        return alreadyRegistration;
    }

    public void setAlreadyRegistration(boolean alreadyRegistration) {
        this.alreadyRegistration = alreadyRegistration;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "serialVersionUID=" + serialVersionUID +
                ", student=" + student +
                ", answers=" + Arrays.toString(answers) +
                ", isRights=" + Arrays.toString(isRights) +
                ", alreadyRegistration=" + alreadyRegistration +
                '}';
    }
}
