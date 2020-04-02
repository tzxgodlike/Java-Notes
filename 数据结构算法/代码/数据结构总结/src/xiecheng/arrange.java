package xiecheng;

import java.util.*;

public  class arrange {

    public static class phone {
        public int start;
        public int end;


        public phone(int start, int end) {
            this.start = start;
            this.end = end;

        }
    }

public static  class ProgramComparator implements Comparator<phone> {

    @Override
    public int compare(phone o1, phone o2) {
        return o1.end - o2.end;
    }

}
    //按最先结束的排序
    public static int bestArrange(phone[] phones, int start) {
        Arrays.sort(phones, new ProgramComparator());
        int result = 0;

        for (int i = 0; i < phones.length; i++) {
            if (start <= phones[i].start) {
                result++;

                start = phones[i].end;

            }
        }
        return result;
    }

    public static int bestArrange1(phone[] phones, int start) {
        if(phones==null||phones.length==0) return 0;
        Arrays.sort(phones, new ProgramComparator());
        int result = 0;
        int cont = 0;
        int[] hasVisted = new int[phones.length];
        Arrays.fill(hasVisted,0);

            for (int n = 0; n <phones.length; n++) {
                if (hasVisted[n]==0){
                for (int i = n; i < phones.length; i++) {
                    if (start <= phones[i].start && hasVisted[i]==0) {
                        result++;
                        hasVisted[i] = 1;
                        start = phones[i].end;

                    }
                }
                cont++;
            }

            }

        return cont;
    }

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        phone[] phones = new phone[n];




        for (int i = 0;i<n;i++) {
            String[] s = in.next().split(",");

            int[] number = new int[s.length];
            for (int j = 0; j < s.length; j++) {
                number[j] = Integer.parseInt(s[j]);
            }

            phones[i] = new phone(number[0],number[1]);
        }
        System.out.println(bestArrange1(phones,0));
        }


    }

