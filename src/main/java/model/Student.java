package model;

import java.io.Serializable;

public class Student implements Serializable {
    static final long serialVersionUID = 1L;
    private String maSV;
    private String hovaten;
    private String IP;
    private int group;
    private String rmiName;
    private int rmiPort;
    private String strExamCode1;
    private String strExamCode2;
    private int numericCode3;
    private int numericCode4;
    private int[] numericExam;
    boolean isAlreadyRegistration = false;

    public int getRmiPort() {
        return this.rmiPort;
    }

    public void setRmiPort(int rmiPort) {
        this.rmiPort = rmiPort;
    }

    public String getRmiName() {
        return this.rmiName;
    }

    public void setRmiName(String rmiName) {
        this.rmiName = rmiName;
    }

    public String getStrExamCode1() {
        return this.strExamCode1;
    }

    public void setStrExamCode1(String strExamCode1) {
        this.strExamCode1 = strExamCode1;
    }

    public String getStrExamCode2() {
        return this.strExamCode2;
    }

    public void setStrExamCode2(String strExamCode2) {
        this.strExamCode2 = strExamCode2;
    }

    public int getNumericCode3() {
        return this.numericCode3;
    }

    public void setNumericCode3(int numericCode3) {
        this.numericCode3 = numericCode3;
    }

    public int getNumericCode4() {
        return this.numericCode4;
    }

    public void setNumericCode4(int numericCode4) {
        this.numericCode4 = numericCode4;
    }

    public boolean isIsAlreadyRegistration() {
        return this.isAlreadyRegistration;
    }

    public void setIsAlreadyRegistration(boolean isAlreadyRegistration) {
        this.isAlreadyRegistration = isAlreadyRegistration;
    }

    public int[] getNumericExam() {
        return this.numericExam;
    }

    public void setNumericExam(int[] numericExam) {
        this.numericExam = numericExam;
    }

    public int getGroup() {
        return this.group;
    }

    public Student(String maSV, String hovaten, String IP, int group, String rmiName, int rmiPort) {
        this.maSV = maSV;
        this.hovaten = hovaten;
        this.IP = IP;
        this.group = group;
        this.numericExam = new int[5];
        this.rmiName = rmiName;
        this.rmiPort = rmiPort;
    }

    public String getMaSV() {
        return this.maSV;
    }

    public String getHovaten() {
        return this.hovaten;
    }

    public String getIP() {
        return this.IP;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setNhom(int nhom) {
        this.group = nhom;
    }
}
