package pdd;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class pinzhengfangxing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0;i<t;i++) {
            int n = sc.nextInt();
            int[] gunzi = new int[n];
            for (int j = 0;j<n;j++) {
                gunzi[j] = sc.nextInt();
            }
            boolean flag = isMatrix(gunzi,n);
            if (flag==true) System.out.println("YES");
            else System.out.println("NO");

        }
    }

    public static boolean isMatrix(int[] m,int len){
        int res = 0;
        for (int i = 0;i<len;i++){
            res+=m[i];
        }
        if (res/4*4!=res) return false;
        Integer[] newNum = new Integer[len];
        for (int i = 0;i<len;i++) {
            newNum[i] = m[i];
        }
        //Arrays.sort(m);
        Arrays.sort(newNum, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        return dfs(newNum,0,len,0,0,0,0,res/4);
//        Arrays.sort(m);
//        int bianchang = res/4;
//        int cnt = 0;
//        int tmp = 0;
//        for (int i = 0;i<len;i++) {
//            tmp += m[i];
//            if (tmp==bianchang) {
//                cnt++;
//                tmp = 0;
//            }
//        }
//        if (cnt==4) return "YES";
//        else return "NO";
        //else return "YES";
    }

    private static boolean dfs(Integer[] m, int i, int len, int i1, int i2, int i3, int i4, int i5) {

        if (i == len) {
            if (i1==i5&&i2==i5&&i3==i5&&i4==i5) {
                return true;
            }else return false;
        }
        if (i1>i5||i2>i5||i3>i5||i4>i5) {
            return false;
        }
        return dfs(m,i+1,len,i1+m[i],i2,i3,i4,i5)||
                dfs(m,i+1,len,i1,i2+m[i],i3,i4,i5)||
                dfs(m,i+1,len,i1,i2,i3+m[i],i4,i5)||
                dfs(m,i+1,len,i1,i2,i3,i4+m[i],i5);
    }
}
