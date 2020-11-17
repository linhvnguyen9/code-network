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
}
