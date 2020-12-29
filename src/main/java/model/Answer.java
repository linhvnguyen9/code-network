package model;

import java.io.Serializable;

public class Answer implements Serializable {
    static final long serialVersionUID = 2L;
    Student student;
    String reverseStringAnswer;
    String normalizationStringAnswer;
    int maxNumericAnswer;
    int uclnNumericAnswer;
    int bcnnNumericAnswer;
    boolean isAlreadyRegistration = false;
    boolean isReverseStringAnswerRight = false;
    boolean isMaxNumericAnswerRight = false;
    boolean isNormalizationStringAnswerRight = false;
    boolean isBSCNNNumericAnswerRight = false;
    boolean isUSCLNNumericAnswerRight = false;

    public Answer() {
    }

    public boolean isIsAlreadyRegistration() {
        return this.isAlreadyRegistration;
    }

    public void setIsAlreadyRegistration(boolean isAlreadyRegistration) {
        this.isAlreadyRegistration = isAlreadyRegistration;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStringAnswer() {
        return this.reverseStringAnswer;
    }

    public void setStringAnswer(String stringAnswer) {
        this.reverseStringAnswer = stringAnswer;
    }

    public int getNumericAnswer() {
        return this.maxNumericAnswer;
    }

    public void setNumericAnswer(int numericAnswer) {
        this.maxNumericAnswer = numericAnswer;
    }

    public boolean isIsStringAnswerRight() {
        return this.isReverseStringAnswerRight;
    }

    public void setIsStringAnswerRight(boolean isStringAnswerRight) {
        this.isReverseStringAnswerRight = isStringAnswerRight;
    }

    public boolean isIsNumericAnswerRight() {
        return this.isMaxNumericAnswerRight;
    }

    public void setIsNumericAnswerRight(boolean isNumericAnswerRight) {
        this.isMaxNumericAnswerRight = isNumericAnswerRight;
    }

    public boolean isIsReverseStringAnswerRight() {
        return this.isReverseStringAnswerRight;
    }

    public void setIsReverseStringAnswerRight(boolean isReverseStringAnswerRight) {
        this.isReverseStringAnswerRight = isReverseStringAnswerRight;
    }

    public boolean isIsMaxNumericAnswerRight() {
        return this.isMaxNumericAnswerRight;
    }

    public void setIsMaxNumericAnswerRight(boolean isMaxNumericAnswerRight) {
        this.isMaxNumericAnswerRight = isMaxNumericAnswerRight;
    }

    public boolean isIsNormalizationStringAnswerRight() {
        return this.isNormalizationStringAnswerRight;
    }

    public void setIsNormalizationStringAnswerRight(boolean isNormalizationStringAnswerRight) {
        this.isNormalizationStringAnswerRight = isNormalizationStringAnswerRight;
    }

    public boolean isIsBSCNNNumericAnswerRight() {
        return this.isBSCNNNumericAnswerRight;
    }

    public void setIsBSCNNNumericAnswerRight(boolean isBSCNNNumericAnswerRight) {
        this.isBSCNNNumericAnswerRight = isBSCNNNumericAnswerRight;
    }

    public boolean isIsUSCLNNumericAnswerRight() {
        return this.isUSCLNNumericAnswerRight;
    }

    public void setIsUSCLNNumericAnswerRight(boolean isUSCLNNumericAnswerRight) {
        this.isUSCLNNumericAnswerRight = isUSCLNNumericAnswerRight;
    }

    public String getReverserStringAnswer() {
        return this.reverseStringAnswer;
    }

    public void setReverserStringAnswer(String reverserStringAnswer) {
        this.reverseStringAnswer = reverserStringAnswer;
    }

    public String getNormalizationStringAnswer() {
        return this.normalizationStringAnswer;
    }

    public void setNormalizationStringAnswer(String normalizationStringAnswer) {
        this.normalizationStringAnswer = normalizationStringAnswer;
    }

    public int getMaxNumericAnswer() {
        return this.maxNumericAnswer;
    }

    public void setMaxNumericAnswer(int maxNumericAnswer) {
        this.maxNumericAnswer = maxNumericAnswer;
    }

    public int getUclnNumericAnswer() {
        return this.uclnNumericAnswer;
    }

    public void setUclnNumericAnswer(int uclnNumericAnswer) {
        this.uclnNumericAnswer = uclnNumericAnswer;
    }

    public int getBcnnNumericAnswer() {
        return this.bcnnNumericAnswer;
    }

    public void setBcnnNumericAnswer(int bcnnNumericAnswer) {
        this.bcnnNumericAnswer = bcnnNumericAnswer;
    }
}
