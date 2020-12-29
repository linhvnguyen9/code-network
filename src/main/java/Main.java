import control.IRMIServer;
import lombok.SneakyThrows;
import model.Answer;
import model.ServerConfiguration;
import model.Student;

import java.io.*;
import java.net.Socket;
import java.rmi.Naming;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static final int NO_OF_CHARS = 256;

    @SneakyThrows
    public static void main(String[] args) {
        IRMIServer service = (IRMIServer) Naming.lookup("rmi://10.170.77.16:4444/Ca2_Server");
        System.out.println("Connection success!");
        Student student = new Student("B17DCCN436", "Le Vu Nam", "10.170.77.11", 2);
        ServerConfiguration config = new ServerConfiguration(0,0,0,0);

        ServerConfiguration response1 = service.getObjectServerDes(student, config);
        ServerConfiguration response2 = service.getStringServerDes(student, config);
        ServerConfiguration response3 = service.getNumericServerDes(student, config);
        System.out.println("PORT: " + response1.toString()
            + " - " + response2.toString()
            + " - " + response3.toString());

        //TODO: Server1
        Socket socket1 = new Socket("10.170.77.16", response1.getObjectServerPort());

        ObjectInputStream ois1 = new ObjectInputStream(socket1.getInputStream());
        ObjectOutputStream oos1 = new ObjectOutputStream(socket1.getOutputStream());
        oos1.writeObject(student);
        Answer answer1 = (Answer) ois1.readObject();
        System.out.println(answer1.toString());


        //TODO: Server2
        Socket socket2 = new Socket("10.170.77.16", response2.getStringServerPort());

        ObjectInputStream ois2 = new ObjectInputStream(socket2.getInputStream());
        ObjectOutputStream oos2 = new ObjectOutputStream(socket2.getOutputStream());
        DataInputStream dis2 = new DataInputStream(socket2.getInputStream());
        DataOutputStream dos2 = new DataOutputStream(socket2.getOutputStream());

        dos2.writeUTF(student.getMaSV());
        dos2.writeUTF(student.getHovaten());
        dos2.writeInt(student.getGroup());
        dos2.writeInt(response2.getCode());

        if (response2.getCode() == 0){
            stringCode0(dos2, dis2);
        }else if (response2.getCode() == 1){
            stringCode1(dos2, dis2);
        }

        Answer answer2 = (Answer) ois2.readObject();
        System.out.println(answer2.toString());

        //TODO: Server3
        Socket socket3 = new Socket("10.170.77.16", response3.getNumericServerPort());

        ObjectInputStream ois3 = new ObjectInputStream(socket3.getInputStream());
        ObjectOutputStream oos3 = new ObjectOutputStream(socket3.getOutputStream());
        DataInputStream dis3 = new DataInputStream(socket3.getInputStream());
        DataOutputStream dos3 = new DataOutputStream(socket3.getOutputStream());

        dos3.writeUTF(student.getMaSV());
        dos3.writeUTF(student.getHovaten());
        dos3.writeInt(student.getGroup());
        dos3.writeInt(response3.getCode());

        if (response3.getCode() == 0){
            numericCode0(dos3, dis3);
        }else if (response3.getCode() == 1){
            numericCode1(dos3, dis3);
        }
        Answer answer3 = (Answer) ois3.readObject();
        System.out.println(answer3.toString());
    }

    public static void stringCode0(DataOutputStream dos, DataInputStream dis) throws IOException {
        String xau1 = dis.readUTF();
        int number = dis.readInt();

        String ceasar = caesarCipher(xau1, number);
        dos.writeUTF(ceasar);

        Object xau2 = dis.readUTF();
        Object number1 = dis.readInt();
        Object number2 = dis.readInt();

        dos.writeUTF(((String) xau2).substring((int) number1, (int) number2));

        String xau3 = dis.readUTF();
        char character = getSecondMostFreq(xau3);
        dos.writeChar(character);
    }

    public static void stringCode1(DataOutputStream dos, DataInputStream dis) throws IOException {
        String xau1 = dis.readUTF();
        char character = getSecondMostFreq(xau1);
        dos.writeChar(character);

        Object xau2 = dis.readUTF();
        Object number1 = dis.readInt();
        Object number2 = dis.readInt();

        dos.writeUTF(((String) xau2).substring((int) number1, (int) number2));

        String xau3 = dis.readUTF();
        int number = dis.readInt();
        String ceasar = caesarCipher(xau3, number);
        dos.writeUTF(ceasar);
    }

    public static void numericCode0(DataOutputStream dos, DataInputStream dis) throws IOException {
        String xau1 = dis.readUTF();
        String xau2 = dis.readUTF();

        int sum1 = sumOfNumberInString(xau1);
        int sum2 = sumOfNumberInString(xau2);

        boolean checkNguyenToCungNhau = nguyenToCungNhau(sum1, sum2);

        dos.writeBoolean(checkNguyenToCungNhau);

        String xau3 = dis.readUTF();

        if (returnCode(xau3)){
            dos.writeInt(1);
        }else {
            dos.writeInt(0);
        }
    }

    public static void numericCode1(DataOutputStream dos, DataInputStream dis) throws IOException {
        String xau3 = dis.readUTF();

        if (returnCode(xau3)){
            dos.writeInt(1);
        }else {
            dos.writeInt(0);
        }

        String xau1 = dis.readUTF();
        String xau2 = dis.readUTF();

        int sum1 = sumOfNumberInString(xau1);
        int sum2 = sumOfNumberInString(xau2);

        boolean checkNguyenToCungNhau = nguyenToCungNhau(sum1, sum2);

        dos.writeBoolean(checkNguyenToCungNhau);
    }

    public static char getSecondMostFreq(String str)
    {
        int[] count = new int[NO_OF_CHARS];
        int i;
        for (i=0; i< str.length(); i++)
            (count[str.charAt(i)])++;

        int first = 0, second = 0;
        for (i = 0; i < NO_OF_CHARS; i++)
        {
            if (count[i] > count[first])
            {
                second = first;
                first = i;
            }

            else if (count[i] > count[second] &&
                count[i] != count[first])
                second = i;
        }

        return (char)second;
    }

    public static String caesarCipher(String str, int shift){
        StringBuilder result = new StringBuilder();
        char[] chars = str.toCharArray();

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
                result.append(cipherChar);
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public static boolean nguyenToCungNhau(int a, int b){
        if (b == 0){
            return (a == 1);
        }
        return nguyenToCungNhau(b, a % b);
    }

    public static int sumOfNumberInString(String str){
        int sum = 0;
        for (Character character : str.toCharArray()){
            sum = sum + stringNUmberToNumber(character);
        }
        return sum;
    }

    public static boolean returnCode(String strCode){
        String number = "";
        String[] numbers = strCode.split(" ");
        for (String value : numbers){
            number = number.concat(stringToNumber(value));
        }

        return !number.contains("-");
    }

    private static String stringToNumber(String number){
        number = number.toLowerCase();
        switch (number){
            case "khong":
                return "0";
            case "mot":
                return "1";
            case "hai":
                return "2";
            case "ba":
                return "3";
            case "bon":
                return "4";
            case "nam":
                return "5";
            case "sau":
                return "6";
            case "bay":
                return "7";
            case "tam":
                return "8";
            case "chin":
                return "9";
            default:
                return "-";
        }
    }

    private static int stringNUmberToNumber(Character number){
        switch (number){
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 0;
        }
    }



    //Bai khac
    public static Character sortByValueDesc(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        return list.get(1).getKey();
    }

    public static Character sortByValue(HashMap<Character, Integer> hashMap)
    {
        List<Map.Entry<Character, Integer> > list = new LinkedList<>(hashMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list.get(list.size() - 2).getKey();
    }

    public static String reverseString(String s){
        return new StringBuilder(s).reverse().toString();
    }

    public static String capitalize(String s){
        List<String> list = Arrays.asList(s.split(" "));

        return list.stream()
                .map(Main::capitalizeString)
                .collect(Collectors.joining(" "));
    }

    public static String capitalizeString(String s){
        String s1 = s.substring(0, 1).toUpperCase();
        return s1 + s.substring(1);
    }

    public static int USCLN(int a, int b) {
        if (b == 0) return a;
        return USCLN(b, a % b);
    }

    public static int BSCNN(int a, int b, int c) {
        return (a * b) / c;
    }

    public static int getMaxValue(int[] numbers){
        return Arrays.stream(numbers).max().getAsInt();
    }
}
