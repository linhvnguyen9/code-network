package model;

import java.io.Serializable;

public class Student implements Serializable {
    final long serialVersionUID = 1L;
    String maSv;
    String hovaten;
    String IP;
    int group;

    public Student(String maSv, String hovaten, String IP, int group) {
        this.maSv = maSv;
        this.hovaten = hovaten;
        this.IP = IP;
        this.group = group;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "serialVersionUID=" + serialVersionUID +
                ", maSv='" + maSv + '\'' +
                ", hovaten='" + hovaten + '\'' +
                ", IP='" + IP + '\'' +
                ", group=" + group +
                '}';
    }
}
