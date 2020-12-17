package model;

import java.io.Serializable;

public class Answer implements Serializable {
    private static final long serialVersionUID = 2L;
    private Student student;
    private String reverseStringAnswer;
    private String normalizationStringAnswer;
    private int maxNumericAnswer;
    private int uclnNumericAnswer;
    private int bcnnNumericAnswer;
    private boolean isAlreadyRegistration;
    private boolean isReverseStringAnswerRight;
    private boolean isMaxNumericAnswerRight;
    private boolean isNormalizationStringAnswerRight;
    private boolean isBSCNNNumericAnswerRight;
    private boolean isUSCLNNumericAnswerRight;

    public Answer() {
    }

    public Answer(Student student, String reverseStringAnswer, String normalizationStringAnswer, int maxNumericAnswer, int uclnNumericAnswer, int bcnnNumericAnswer, boolean isAlreadyRegistration, boolean isReverseStringAnswerRight, boolean isMaxNumericAnswerRight, boolean isNormalizationStringAnswerRight, boolean isBSCNNNumericAnswerRight, boolean isUSCLNNumericAnswerRight) {
        this.student = student;
        this.reverseStringAnswer = reverseStringAnswer;
        this.normalizationStringAnswer = normalizationStringAnswer;
        this.maxNumericAnswer = maxNumericAnswer;
        this.uclnNumericAnswer = uclnNumericAnswer;
        this.bcnnNumericAnswer = bcnnNumericAnswer;
        this.isAlreadyRegistration = isAlreadyRegistration;
        this.isReverseStringAnswerRight = isReverseStringAnswerRight;
        this.isMaxNumericAnswerRight = isMaxNumericAnswerRight;
        this.isNormalizationStringAnswerRight = isNormalizationStringAnswerRight;
        this.isBSCNNNumericAnswerRight = isBSCNNNumericAnswerRight;
        this.isUSCLNNumericAnswerRight = isUSCLNNumericAnswerRight;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getReverseStringAnswer() {
        return reverseStringAnswer;
    }

    public void setReverseStringAnswer(String reverseStringAnswer) {
        this.reverseStringAnswer = reverseStringAnswer;
    }

    public String getNormalizationStringAnswer() {
        return normalizationStringAnswer;
    }

    public void setNormalizationStringAnswer(String normalizationStringAnswer) {
        this.normalizationStringAnswer = normalizationStringAnswer;
    }

    public int getMaxNumericAnswer() {
        return maxNumericAnswer;
    }

    public void setMaxNumericAnswer(int maxNumericAnswer) {
        this.maxNumericAnswer = maxNumericAnswer;
    }

    public int getUclnNumericAnswer() {
        return uclnNumericAnswer;
    }

    public void setUclnNumericAnswer(int uclnNumericAnswer) {
        this.uclnNumericAnswer = uclnNumericAnswer;
    }

    public int getBcnnNumericAnswer() {
        return bcnnNumericAnswer;
    }

    public void setBcnnNumericAnswer(int bcnnNumericAnswer) {
        this.bcnnNumericAnswer = bcnnNumericAnswer;
    }

    public boolean isAlreadyRegistration() {
        return isAlreadyRegistration;
    }

    public void setAlreadyRegistration(boolean alreadyRegistration) {
        isAlreadyRegistration = alreadyRegistration;
    }

    public boolean isReverseStringAnswerRight() {
        return isReverseStringAnswerRight;
    }

    public void setReverseStringAnswerRight(boolean reverseStringAnswerRight) {
        isReverseStringAnswerRight = reverseStringAnswerRight;
    }

    public boolean isMaxNumericAnswerRight() {
        return isMaxNumericAnswerRight;
    }

    public void setMaxNumericAnswerRight(boolean maxNumericAnswerRight) {
        isMaxNumericAnswerRight = maxNumericAnswerRight;
    }

    public boolean isNormalizationStringAnswerRight() {
        return isNormalizationStringAnswerRight;
    }

    public void setNormalizationStringAnswerRight(boolean normalizationStringAnswerRight) {
        isNormalizationStringAnswerRight = normalizationStringAnswerRight;
    }

    public boolean isBSCNNNumericAnswerRight() {
        return isBSCNNNumericAnswerRight;
    }

    public void setBSCNNNumericAnswerRight(boolean BSCNNNumericAnswerRight) {
        isBSCNNNumericAnswerRight = BSCNNNumericAnswerRight;
    }

    public boolean isUSCLNNumericAnswerRight() {
        return isUSCLNNumericAnswerRight;
    }

    public void setUSCLNNumericAnswerRight(boolean USCLNNumericAnswerRight) {
        isUSCLNNumericAnswerRight = USCLNNumericAnswerRight;
    }

    @Override
    public String toString() {
        return "Answer{" +
            "student=" + student +
            ", reverseStringAnswer='" + reverseStringAnswer + '\'' +
            ", normalizationStringAnswer='" + normalizationStringAnswer + '\'' +
            ", maxNumericAnswer=" + maxNumericAnswer +
            ", uclnNumericAnswer=" + uclnNumericAnswer +
            ", bcnnNumericAnswer=" + bcnnNumericAnswer +
            ", isAlreadyRegistration=" + isAlreadyRegistration +
            ", isReverseStringAnswerRight=" + isReverseStringAnswerRight +
            ", isMaxNumericAnswerRight=" + isMaxNumericAnswerRight +
            ", isNormalizationStringAnswerRight=" + isNormalizationStringAnswerRight +
            ", isBSCNNNumericAnswerRight=" + isBSCNNNumericAnswerRight +
            ", isUSCLNNumericAnswerRight=" + isUSCLNNumericAnswerRight +
            '}';
    }
}
