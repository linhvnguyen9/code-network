package control;

import java.util.StringTokenizer;

public class CheckAnswer {
        public CheckAnswer() {
        }

        public static String reverse(String str) {
            String tmp = "";

            for(int i = str.length() - 1; i >= 0; --i) {
                tmp = tmp + str.substring(i, i + 1);
            }

            return tmp;
        }

        public static String chuanHoa(String strInput) {
            String strOutput = "";
            StringTokenizer strToken = new StringTokenizer(strInput, " ,\t,\r");

            for(strOutput = strOutput + "" + chuyenInHoa(strToken.nextToken()); strToken.hasMoreTokens(); strOutput = strOutput + " " + chuyenInHoa(strToken.nextToken())) {
            }

            return strOutput;
        }

        public static String chuyenInHoa(String str) {
            String s = str.substring(0, 1);
            String strOutput = str.replaceFirst(s, s.toUpperCase());
            return strOutput;
        }

        public static int USCLN(int a, int b) {
            return b == 0 ? a : USCLN(b, a % b);
        }

        public static int BSCNN(int a, int b) {
            return a * b / USCLN(a, b);
        }

        public static boolean checkMaxNumericAnswer(int[] numericExam, int respond) {
            if (numericExam == null) {
                return false;
            } else {
                int maxValue = numericExam[0];

                for(int i = 1; i < numericExam.length; ++i) {
                    if (maxValue < numericExam[i]) {
                        maxValue = numericExam[i];
                    }
                }

                if (maxValue == respond) {
                    return true;
                } else {
                    return false;
                }
            }
        }
}
