import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IOTemplate {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
//        while(in.hasNext()) {
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
//        ArrayList<Integer> list = new ArrayList();
//        list.add(1);
//        list.get(0);
        //用bufferreader  https://blog.csdn.net/seableble/article/details/103172385?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
        //https://ac.nowcoder.com/acm/contest/320

        /*
        输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据包括多组。
        1 5
        10 20
        20 30
         */
        Scanner in1 = new Scanner(System.in);
        while (in1.hasNext()) {
            int a = in1.nextInt();
            int b = in1.nextInt();
            System.out.println(a+b);
        }
        /*
        输入第一行包括一个数据组数t(1 <= t <= 100)
        接下来每行包括两个正整数a,b(1 <= a, b <= 10^9)
        2
        1 5
        10 20
         */
        Scanner in2 = new Scanner(System.in);
        int lineNum  = in2.nextInt();
        for (int i = 0;i<lineNum;i++) {
            int a = in2.nextInt();
            int b = in2.nextInt();
            System.out.println(a+b);
        }
    }

    /*
    输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
    1 5
    10 20
    0 0
     */
//    Scanner in3 = new Scanner(System.in);
//        while (in3.hasNext()){
//            int a = in3.nextInt();
//            int b = in3.nextInt();
//            if (a == 0 && b == 0) {
//                break;
//            }else {
//                System.out.println(a + b);
//            }
//    }

    /*

    输入数据包括多组。
    每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
    接下来n个正整数,即需要求和的每个正整数。
    4 1 2 3 4
    5 1 2 3 4 5
    0
     */
//    Scanner in = new Scanner(System.in);
//        while(in.hasNext()){
//        int num = in.nextInt();
//        if(num==0) break;
//        int res = 0;
//        for (int i = 0;i<num;i++){
//            res+=in.nextInt();
//        }
//        System.out.println(res);
//    }

    /*
输入的第一行包括一个正整数t(1 <= t <= 100), 表示数据组数。
接下来t行, 每行一组数据。
每行的第一个整数为整数的个数n(1 <= n <= 100)。
接下来n个正整数, 即需要求和的每个正整数。
2
4 1 2 3 4
5 1 2 3 4 5
     */
//    Scanner in = new Scanner(System.in);
//    int num = in.nextInt();
//        for(int i = 0;i<num;i++){
//        int n = in.nextInt();
//        int res = 0;
//        for (int j= 0;j<n;j++){
//            res+=in.nextInt();
//        }
//        System.out.println(res);
//    }

    /*
输入数据有多组, 每行表示一组输入数据。
每行的第一个整数为整数的个数n(1 <= n <= 100)。
接下来n个正整数, 即需要求和的每个正整数。
4 1 2 3 4
5 1 2 3 4 5
     */
//    Scanner in = new Scanner(System.in);
//        while(in.hasNext()) {
//        int num = in.nextInt();
//        int res = 0;
//        for (int i = 0;i<num;i++){
//            res+=in.nextInt();
//        }
//        System.out.println(res);
//    }


    /*
输入数据有多组, 每行表示一组输入数据。

每行不定有n个整数，空格隔开。(1 <= n <= 100)。
1 2 3
4 5
0 0 0 0 0


    当读入的为不定长的数字时 需要用nextLine读取为字符串 再去掉括号 变成int
     */
//    Scanner in = new Scanner(System.in);
//        while(in.hasNextLine()){
//        String[] s = in.nextLine().split(" ");
//        int res = 0;
//        for (int i = 0;i<s.length;i++) {
//            res+=Integer.parseInt(s[i]);
//        }
//        System.out.println(res);
//    }


/*
输入有两行，第一行n

第二行是n个空格隔开的字符串
5
c d a bb e
 */
//    Scanner in = new Scanner(System.in);
//    int n = in.nextInt();
//    String[] s = new String[n];
//        for(int i = 0;i<n;i++){
//        s[i] = in.next();
//    }
//        Arrays.sort(s);
//        for (int i = 0; i < n; ++i) {
//        if (i == n - 1) {
//            System.out.println(s[i]);
//            break;
//        }
//        System.out.print(s[i] + " ");
//    }

    /*
    多个测试用例，每个测试用例一行。

    每行通过空格隔开，有n个字符，n＜100
    a c bb
    f dddd
    nowcoder
     */
//    Scanner in = new Scanner(System.in);
//        while(in.hasNextLine()){
//        String[] s = in.nextLine().split(" ");
//        Arrays.sort(s);
//        for (int i = 0; i < s.length; ++i) {
//            if (i ==  s.length - 1) {
//                System.out.println(s[i]);
//                break;
//            }
//            System.out.print(s[i] + " ");
//        }
//    }

    /*
    多个测试用例，每个测试用例一行。
每行通过,隔开，有n个字符，n＜100
a,c,bb
f,dddd
nowcoder
     */
//    Scanner in = new Scanner(System.in);
//        while(in.hasNextLine()){
//        String[] s = in.nextLine().split(",");
//        Arrays.sort(s);
//        for (int i = 0; i < s.length; ++i) {
//            if (i ==  s.length - 1) {
//                System.out.println(s[i]);
//                break;
//            }
//            System.out.print(s[i] + ",");
//        }
//    }













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



