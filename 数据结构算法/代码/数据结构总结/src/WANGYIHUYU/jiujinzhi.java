package WANGYIHUYU;

public class jiujinzhi {

    public static String addP(String s1,String s2) {
        // 一个字符串为空 直接返回另外一个
        if (s1 == null || "".equals(s1)) {
            return s2;
        }
        if (s2 == null || "".equals(s2)) {
            return s1;
        }
        String[] s11 = s1.split(".");
        String s1_xiaoshu = s11[1];
        String s1_zhengshu = s11[0];

        String[] s22 = s1.split(".");
        String s2_xiaoshu = s22[1];
        String s2_zhengshu = s22[0];

        return "";
    }

    public static void main(String[] args) {
        //String res = add("1.28","1.71");
        String res = add("0.5","0.5");
        System.out.println(res);
    }
    public static String add(String s1,String s2) {
        // 一个字符串为空 直接返回另外一个
        if (s1 == null || "".equals(s1)) {
            return s2;
        }
        if (s2 == null || "".equals(s2)) {
            return s1;
        }
        StringBuilder sb = new StringBuilder();



        boolean toTen_xiaoshu = false;

        boolean toTen_zhengshu = false;

        boolean hasXiaoshu = true;
        String[] s11 = s1.split("\\.");
        String[] s22 = s2.split("\\.");

        String s1_xiaoshu;
        String s1_zhengshu;

        String s2_xiaoshu;
        String s2_zhengshu;
        //没有小数
        if (s11.length==1&&s22.length==1) {
            s1_xiaoshu="0";
            s2_xiaoshu="0";
        }
        if (s11.length==1 ) s1_xiaoshu="0";
        else s1_xiaoshu = s11[1];
        if (s22.length==1 ) s2_xiaoshu="0";
        else s2_xiaoshu = s22[1];
        //System.out.println(s11.length);

        s1_zhengshu = s11[0];


        s2_zhengshu = s22[0];


        char[] c1 = s1_xiaoshu.toCharArray();
        char[] c2 = s2_xiaoshu.toCharArray();
        int idx1 = c1.length-1;
        int idx2 = c2.length-1;
        while (idx1>=0&&idx2>=0) {
            char cur1 = c1[idx1];
            char cur2 = c2[idx2];
            int sum = cur1-'0'+cur2-'0';
            //上一次是否有进位
            sum = toTen_xiaoshu?sum+1:sum;
            //是否需要进位
            toTen_xiaoshu = (sum>=9)?true:false;
            sb.append((char)(sum%9+'0'));
            idx1--;
            idx2--;
        }
        System.out.println(sb.toString());

        //处理剩余元素 c1或c2还剩 或者 c1c2加完但是最后产生一个进位
        while (toTen_xiaoshu||idx1>=0||idx2>=0) {  //要带等号
            if (idx1>=0) {
                int sum = c1[idx1]-'0'+(toTen_xiaoshu?1:0);
                toTen_xiaoshu = (sum>=9)?true:false;
                sb.append((char)(sum%9+'0'));
                idx1--;
            }else if (idx2>=0) {
                int sum = c2[idx2]-'0'+(toTen_xiaoshu?1:0);
                toTen_xiaoshu = (sum>=9)?true:false;
                sb.append((char)(sum%9+'0'));
                idx2--;
            }else {
                //sb.append('1');
                toTen_zhengshu = true;
                toTen_xiaoshu = false;
            }
        }
        System.out.println(toTen_zhengshu);

        StringBuilder sb_zs = new StringBuilder();


        //if (toTen_xiaoshu==true)  toTen_zhengshu = true;
        //处理整数
        char[] c1_zs = s1_zhengshu.toCharArray();
        char[] c2_zs = s2_zhengshu.toCharArray();
        int idx1_zs = c1_zs.length-1;
        int idx2_zs = c2_zs.length-1;

        //System.out.println();
        while (idx1_zs>=0&&idx2_zs>=0) {
            char cur1 = c1_zs[idx1_zs];
            char cur2 = c2_zs[idx2_zs];
            int sum = cur1-'0'+cur2-'0';
            //上一次是否有进位
            sum = toTen_zhengshu?sum+1:sum;
            //是否需要进位
            toTen_zhengshu = (sum>=9)?true:false;
            sb_zs.append((char)(sum%9+'0'));
            idx1_zs--;
            idx2_zs--;
        }
        //处理剩余元素 c1或c2还剩 或者 c1c2加完但是最后产生一个进位
        while (toTen_zhengshu||idx1_zs>=0||idx2_zs>=0) {  //要带等号
            if (idx1_zs>=0) {
                int sum = c1_zs[idx1_zs]-'0'+(toTen_zhengshu?1:0);
                toTen_zhengshu = (sum>=9)?true:false;
                sb_zs.append((char)(sum%9+'0'));
                idx1_zs--;
            }else if (idx2_zs>=0) {
                int sum = c2_zs[idx2_zs]-'0'+(toTen_zhengshu?1:0);
                toTen_zhengshu = (sum>=9)?true:false;
                sb_zs.append((char)(sum%9+'0'));
                idx2_zs--;
            }else {
                sb_zs.append('1');
                toTen_zhengshu = false;
            }
        }
        String res;
        if (s11.length==1&&s22.length==1){
            res = sb_zs.reverse().toString();
        }else {
            res = sb_zs.reverse().toString() + "." + sb.reverse().toString();
        }
        return res;
    }
}
