package Chapter2;

public class ReplaceSpace_05 {
    /**
     * 遍历每一个字符，如果是空格就使用Java库函数replace替换
     *
     * @param str 原字符串
     * @return 替换空格后的字符串
     */
    public String replaceSpace2(StringBuffer str) {
        if (str == null) {
            return null;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, "%20");
            }
        }
        return str.toString();
    }

    /**
     * 更加简洁的库函数方法
     */
    public String replaceSpace3(StringBuffer str) {
        return str.toString().replaceAll("\\s", "%20");
    }

    /**
     * 库函数很方便，但是也要理解原理，所以掌握下面的方法是很有必要的。
     * 1、统计空格数目
     * 2、增长原字符串的长度
     * 3、两个指针，一个指向原来字符串末尾，一个指向新字符串末尾。从后往前扫描字符串，并左移指针
     * 4、需要将原字符串扩容 所以使用stringBuilder
     */
    public static String replaceSpace(StringBuilder str){
        if (str ==null){
            return null;
        }
        //char[] chs = str.toCharArray();
        //统计空格个数
        int spaceNum = 0;
        for (int i = 0;i < str.length();i ++){
           if (str.charAt(i) == ' ')
               spaceNum++;
        }
        //原末尾位置
        int oldP = str.length() - 1;
        //字符串扩容
        str.setLength(str.length()+2*spaceNum);
        //新末尾位置
        int newP = str.length()-1;
        //p1==p2时 说明已经不需要移动
        while (oldP!=newP&&oldP>=0){
            if (str.charAt(oldP)!=' '){
                str.setCharAt(newP--,str.charAt(oldP));
            }else {
                str.setCharAt(newP--,'0');
                str.setCharAt(newP--,'2');
                str.setCharAt(newP--,'%');
            }
            oldP--;
        }
        return str.toString();
    }
}

