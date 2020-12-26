package algo;

public class CaesarCipher {
    public static void main(String[] args) {
        String string1 = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
        int shift = -3;

        char[] chars = string1.toCharArray();

        for (char currentChar : chars) {
            if (Character.isLetter(currentChar)) {
                int currentCharCode = (int) currentChar - 65;
                int shiftedCharCode = currentCharCode + shift;
                if (shiftedCharCode < 0) {
                    shiftedCharCode += 26;
                } else if (shiftedCharCode > 25) {
                    shiftedCharCode -= 26;
                }
                int cipherCharCode = shiftedCharCode % 26;
                char cipherChar = (char) (cipherCharCode + 65);
                System.out.print(cipherChar);
            } else {
                System.out.print(currentChar);
            }
        }
    }
}
