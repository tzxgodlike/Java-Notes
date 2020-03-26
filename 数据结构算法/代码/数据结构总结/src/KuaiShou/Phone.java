//import java.util.*;
//public class Phone{
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            String[] s = in.nextLine().split(",");
//
//            String[] phone = new String[s.length];
//            for (int i = 0; i < s.length; i++) {
//                phone[i] = s[i].substring(3);
//
//                System.out.println(phone[i]);
//            }
//            int[] res = sortPhone(phone);
//        }
//    }
//
//    boolean hasValue(String phone) {
//        if(hasS(phone)>0||hasB(phone)>0){
//            return true;
//        }
//        return false;
//    }
//    int hasS(String phone) {
//        boolean flag = false;
//        int count = 0;
//
//        for (int i=0;i<phone.length()-2;i++) {
//            if(phone[i]+1==phone[i+1]&&phone[i+1]+1==phone[i+2]){
//                flag = true;
//            }
//            if (flag == fasle) return 0;
//            while (i<phone.length){
//                if (phone[i+1]!=phone[i]+1){
//                    break;
//                }else {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//    int hasB(String phone) {
//        boolean flag = false;
//        int count = 0;
//
//        for (int i=0;i<phone.length()-2;i++) {
//            if(phone[i]==phone[i+1]&&phone[i+1]==phone[i+2]){
//                flag = true;
//            }
//            if (flag == fasle) return 0;
//            while (i<phone.length){
//                if (phone[i+1]!=phone[i]){
//                    break;
//                }else {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//    public int[] sortPhone(String[] phone) {
//        //把是靓号的号码加入待比较数组
//        List<String> toSort = new LinkedList<>();
//        for(int i = 0;i<phone.length;i++) {
//            if(hasValue(phone[i])){
//                toSort.add(phone[i]);
//            }
//        }
//    }
//}