package xiecheng;

import java.util.*;

public  class arrange {
    /*
    电话接听最少人员
    跟安排会议时间类似
    [0,30] 给一些类似这样的电话起止时间 求最少几个客服能接全部电话

     */

    /*
    思路：  把开始时间作为一个数组 结束时间作为一个数组 然后按时间顺序推演
    https://www.jianshu.com/p/3948fda91d3d
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];
        //sc.nextLine();
        for (int i = 0;i<n;i++) {
            String[] s = sc.next().split(",");
            start[i] = Integer.parseInt(s[0]);
            end[i] = Integer.parseInt(s[1]);
        }
        int reslut = find(start,end);
        System.out.print(reslut);
    }

    private static int find(int[] start, int[] end) {
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 1;  //start[0]肯定是比end[0]小的
        int j = 0;
        int cur = 1;  //表示当前需要的客服
        int res = 0;
        while (i<start.length&&j<end.length) {
            if (start[i]<end[j]) {
                cur++;
                i++;
                res = Math.max(res,cur);
            }else {
                //说明有一个客服空闲出来
                cur--;
                j++;
            }
        }
        return res;
    }


//    public static class phone {
//        public int start;
//        public int end;
//
//
//        public phone(int start, int end) {
//            this.start = start;
//            this.end = end;
//
//        }
//    }
//
//public static  class ProgramComparator implements Comparator<phone> {
//
//    @Override
//    public int compare(phone o1, phone o2) {
//        return o1.end - o2.end;
//    }
//
//}
//    //按最先结束的排序
//    public static int bestArrange(phone[] phones, int start) {
//        Arrays.sort(phones, new ProgramComparator());
//        int result = 0;
//
//        for (int i = 0; i < phones.length; i++) {
//            if (start <= phones[i].start) {
//                result++;
//
//                start = phones[i].end;
//
//            }
//        }
//        return result;
//    }
//
//    public static int bestArrange1(phone[] phones, int start) {
//        if(phones==null||phones.length==0) return 0;
//        Arrays.sort(phones, new ProgramComparator());
//        int result = 0;
//        int cont = 0;
//        int[] hasVisted = new int[phones.length];
//        Arrays.fill(hasVisted,0);
//
//            for (int n = 0; n <phones.length; n++) {
//                if (hasVisted[n]==0){
//                for (int i = n; i < phones.length; i++) {
//                    if (start <= phones[i].start && hasVisted[i]==0) {
//                        result++;
//                        hasVisted[i] = 1;
//                        start = phones[i].end;
//
//                    }
//                }
//                cont++;
//            }
//
//            }
//
//        return cont;
//    }
//
//    public static void main(String[] args) {
//
//
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        phone[] phones = new phone[n];
//
//
//
//
//        for (int i = 0;i<n;i++) {
//            String[] s = in.next().split(",");
//
//            int[] number = new int[s.length];
//            for (int j = 0; j < s.length; j++) {
//                number[j] = Integer.parseInt(s[j]);
//            }
//
//            phones[i] = new phone(number[0],number[1]);
//        }
//        System.out.println(bestArrange1(phones,0));
//        }


    }

