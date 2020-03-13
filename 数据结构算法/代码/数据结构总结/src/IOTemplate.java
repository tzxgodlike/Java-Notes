import java.util.ArrayList;
import java.util.Scanner;

public class IOTemplate {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.get(0);
    }

    //字符串变为数组


    public static int[] strToIntArr(String str) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        if (!scanner.hasNext()) {
            System.out.println(str1);
            return null;
        }
        String[] strNumber = str.split(",");
        int[] number = new int[strNumber.length];
        for (int i = 0; i < strNumber.length; i++) {
            number[i] = Integer.parseInt(strNumber[i]);
        }

        return number;
    }
}



