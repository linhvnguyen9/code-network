package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maSV;
    private String hovaten;
    private String IP;
    private int group;
    private String strExamCode1;
    private String strExamCode2;
    private int numericCode3;
    private int numericCode4;
    private int[] numericExam;

    public Student(String maSV, String hovaten, String IP, int group) {
        this.maSV = maSV;
        this.hovaten = hovaten;
        this.IP = IP;
        this.group = group;
    }
}
