package Chapter3;

public class Print1ToMaxOfNDigits_17 {
    /**
     * 输入数字n，按顺序打印处1到最大的n位十进制数，比如输入3，则打印1~999之间的数
     */
    public static void printFrom1ToMaxOfNDigit(int n){
        if (n<0) return ;
        StringBuilder sb = new StringBuilder();
        for (int i =0;i<n;i++){
            sb.append("0");
        }
        while (stillIncrease(sb)){
            printNum(sb);
        }
    }
    //自增1
    private static boolean stillIncrease(StringBuilder sb) {
        /*
        如何判断是否达到999... 当sb[0]产生进位时 达到最大
         */
        //toTen是每位的进位
        int toTen = 0;
        int len = sb.length();
        for (int i = len-1;i>=0;i--) {
            int sum = sb.charAt(i) - '0' + toTen;  //是否有进位
            if (i == len-1) sum++;    //个位+1
            if (sum>=10){                //产生进位
                if (i==0) return false;
                /*
                如何判断是否达到999... 当sb[0]产生进位时 达到最大 直接退出
                */
                else {
                    sum -= 10; //sum==0
                    toTen = 1;
                    sb.setCharAt(i,(char) ('0'+sum));
                }
            }else {
                sb.setCharAt(i,(char) ('0'+sum));
                break;
            }
        }
        return true;
    }
    //打印数字 前面的0不打印
    private static void  printNum(StringBuilder sb) {
        int start = sb.length();
        // 找到第一个不为0的索引
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '0') {
                start = i;
                break;
            }
        }
        // 如果全是0，就不会打印
        if (start < sb.length()) {
            System.out.print(sb.substring(start) + " ");
        }
    }

    /*
    全排列的递归方法
     */
    public static void printFrom1ToMaxOfNDigitRecur(int n) {
        if (n<0) return ;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("0");
        }

        printRecur(sb, 0);
        System.out.println();
    }

    private static void printRecur(StringBuilder sb,int index) {
        if (index==sb.length()){
            printNum(sb);
            return ;
        }
        for (int i = 0;i<10;i++){
            sb.setCharAt(index,(char)('0'+i));
            printRecur(sb,index+1);
        }
    }

    /*
    若是两个大数相加呢？
    如何处理进位？ 因为两个数可能位数对应的不一样
    反转字符串 这样个位对应个位 十位对应十位
     */
    public static String add(String s1,String s2) {
        // 一个字符串为空 直接返回另外一个
        if (s1 == null || "".equals(s1)) {
            return s2;
        }
        if (s2 == null || "".equals(s2)) {
            return s1;
        }
        StringBuilder sb = new StringBuilder();

        boolean toTen = false;

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int idx1 = c1.length-1;
        int idx2 = c2.length-1;
        while (idx1>=0&&idx2>=0) {
            char cur1 = c1[idx1];
            char cur2 = c2[idx2];
            int sum = cur1-'0'+cur2-'0';
            //上一次是否有进位
            sum = toTen?sum+1:sum;
            //是否需要进位
            toTen = (sum>=10)?true:false;

            idx1--;
            idx2--;
        }
        //处理剩余元素 c1或c2还剩 或者 c1c2加完但是最后产生一个进位
        while (toTen||idx1>=0||idx2>=0) {  //要带等号
            if (idx1>=0) {
                int sum = c1[idx1]-'0'+(toTen?1:0);
                toTen = (sum>=10)?true:false;
                sb.append((char)(sum%10+'0'));
                idx1--;
            }else if (idx2>=0) {
                int sum = c2[idx1]-'0'+(toTen?1:0);
                toTen = (sum>=10)?true:false;
                sb.append((char)(sum%10+'0'));
                idx2--;
            }else {
                sb.append('1');
                toTen = false;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        //printFrom1ToMaxOfNDigitRecur(2);
        System.out.println(add("11","12"));
    }

}
