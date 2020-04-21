import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IOTemplate {

    public static void main(String[] args) {
        /*
        Collections 工具类

        1.排序
        void reverse(List list)//反转
        void shuffle(List list)//随机排序
        void sort(List list)//按自然排序的升序排序
        void sort(List list, Comparator c)//定制排序，由Comparator控制排序逻辑
        void swap(List list, int i , int j)//交换两个索引位置的元素
        void rotate(List list, int distance)//旋转。当distance为正数时，
        将list后distance个元素整体移到前面。当distance为负数时，将 list的前distance个元素整体移到后面。

        2.查找 替换
        int binarySearch(List list, Object key)//对List进行二分查找，返回索引，注意List必须是有序的
        int max(Collection coll)//根据元素的自然顺序，返回最大的元素。 类比int min(Collection coll)
        int max(Collection coll, Comparator c)//根据定制排序，返回最大元素，排序规则由Comparatator类控制。类比int min(Collection coll, Comparator c)
        void fill(List list, Object obj)//用指定的元素代替指定list中的所有元素。
        int frequency(Collection c, Object o)//统计元素出现次数
        int indexOfSubList(List list, List target)//统计target在list中第一次出现的索引，找不到则返回-1，类比int lastIndexOfSubList(List source, list target).
        boolean replaceAll(List list, Object oldVal, Object newVal), 用新元素替换旧元素


        Arrays 工具类

        排序 : sort()
        查找 : binarySearch()
        比较: equals()
        填充 : fill()
        转列表: asList()
        转字符串 : toString()
        复制: copyOf()

            // *************复制 copy****************
            // copyOf 方法实现数组复制,h为数组，6为复制的长度
            int[] h = { 1, 2, 3, 3, 3, 3, 6, 6, 6, };
            int i[] = Arrays.copyOf(h, 6);
            System.out.println("Arrays.copyOf(h, 6);：");
            // 输出结果：123333
            for (int j : i) {
                System.out.print(j);
            }
            // 换行
            System.out.println();
            // copyOfRange将指定数组的指定范围复制到新数组中
            int j[] = Arrays.copyOfRange(h, 6, 11);
            System.out.println("Arrays.copyOfRange(h, 6, 11)：");
            // 输出结果66600(h数组只有9个元素这里是从索引6到索引11复制所以不足的就为0)
            for (int j2 : j) {
                System.out.print(j2);
            }
            // 换行
            System.out.println();
             */

        /*
    手写构建新的比较规则
            Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // if the heights are equal, compare k-values
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });


        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
     */


        /*
        字符串格式化
         */
        DecimalFormat df = new DecimalFormat("00");
        int num= 0;
        String sresH = df.format(num);
        System.out.println(sresH);   //sresH为 “00”

        /*
        子串匹配
         */
        String parent = "aaaa";
        int cnt = 0;
        int index = 0;
        String kid = "aa";
        while ((index=parent.indexOf(kid,index))!=-1){
            index = index + 1;
            cnt++;
        }
        System.out.println(cnt);


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


/*
    大坑！！！ 先读整数 再读字符串 中间要用sc.nextLine()来清除一次\n

    netx()函数从遇到的第一个有效的字符a开始扫描，直到遇到第一个[空格]扫描结束
    nextLine()函数从空格开始扫描知直到遇到\n符结束
    所以联合使用netx() nextLine()要慎重

如果要求一次接受一个整数、浮点数和字符串？你会怎么做？？
42
3.1415
Welcome to HackerRank Java tutorials!

        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            int x=sc.nextInt();
            double y=sc.nextDouble();
            sc.nextLine();
            //to flush the character \n left by method nextDouble()
            String s= sc.nextLine();
            System.out.println("String: "+s);
            System.out.println("Double: "+y);
            System.out.println("Int: "+x);

        }
        */



/*
用户的输入在计算机缓冲区中是这样存储的：42\n3.1415\nWelcome to HackerRank Java tutorials!\n
使用nextInt()读取42结束，剩下\n3.1415\nWelcome to HackerRank Java
使用nextDouble()读取3.1415，剩下\nWelcome to HackerRank Java
如果此时直接用nextLine()读取，该函数遇到换行符\n就结束
所以需要在这之前输入nextLine()，去掉缓冲区的\n，然后再读取
这是由于nextLine()会读取\n之前的所有内容，并去除\n，而nextDouble()等曾不会去除它后面的\n

 */










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



