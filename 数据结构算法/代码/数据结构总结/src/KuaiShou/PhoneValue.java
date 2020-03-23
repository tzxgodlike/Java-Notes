package KuaiShou;

import java.util.*;

public class PhoneValue {
    static class Phone {
        int[] num;
        boolean hasS = false;
        boolean hasB;
        int Slen;
        int Blen;
        int Sstart;
        int Bstart;
        boolean hasValue;

        Phone(phone){
            num = phone;
        }
        int hasS(){
            if ()
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(",");

            //int[] phone = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                //phone[i] = Integer.parseInt(s[i].substring(3));
                //phone[i] = in
                //System.out.println(phone[i]);
            }
        }
    }
    public static int[] sortPhone(String[] phone) {
        LinkedList<Phone> list = new LinkedList<>();
        for (int i = 0;i<phone.length;i++) {
            Phone p = new Phone(Integer.parseInt(phone[i].substring(3)));
            if (p.hasValue){
                list.add(p);
            }
        }
        Phone[] toSort = new Phone[list.size()];
        for (int i = 0;i<list.size();i++){
            toSort[i] = list.removeFirst();
        }
        Arrays.sort(toSort,new MyComparator());
    }
    public static class MyComparator implements Comparator<Phone> {
        @Override
        public int compare(Phone a, Phone b) {
            //int m = Integer.parseInt(a.substring(3));
            //int n = Integer.parseInt(b.substring(3));
            //Phone p = new Phone(m);
            // q = new Phone(n);
            if (a.hasS)
            //return (a+b).compareTo(b+a);  //1在2前面 则返回负数
        }
    }

}
