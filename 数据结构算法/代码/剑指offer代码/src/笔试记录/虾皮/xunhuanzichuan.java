package 笔试记录.虾皮;

public class xunhuanzichuan {
    public static void main(String[] args) {
        //System.out.println(isxunhuan("abcabc"));
        System.out.println(getMinLen("abcabca"));
    }

    public static int getMinLen (String str) {
        if (str==""||str==null) return 0;
        if (str.length()==1) return 1;
        // write code here
        int res = 0;
        for (int i = 1;i<=str.length();i++) {
            for (int j = 0;j<str.length()-i+1;j++) {
                String s1 = str.substring(j,j+i);
                System.out.println(s1);
                if (isxunhuan(str+s1)) res =  i;
            }
        }
        return res;
    }

    public static boolean isxunhuan(String s) {
        String str = s+s;
        return str.substring(1,str.length()-1).contains(s);
    }
}
