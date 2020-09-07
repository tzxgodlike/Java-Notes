package Leetcode;

public class AddBinar_67 {
    public static String addBinary(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();

        int len1 = c1.length;
        int len2 = c2.length;

        //int len = len1>len2?len1:len2;

        int carry = 0;//进位
        StringBuilder sb = new StringBuilder();
        int i = len1-1,j = len2-1,n1= 0,n2 = 0;
        while (i >=0||j >= 0||carry != 0) {
            n1 = (i >= 0) ?c1[i]-'0':0;
            n2 = (j >= 0) ?c2[j]-'0':0;
            int tmp = n1 + n2 + carry;

            carry = tmp/2;

            sb.append(tmp%2);
            i--;
            j--;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "11";
        String s2 = "1";

        System.out.println(addBinary(s1,s2));
    }
}
