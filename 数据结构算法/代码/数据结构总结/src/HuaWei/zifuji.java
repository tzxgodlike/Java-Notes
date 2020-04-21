package HuaWei;
import java.util.*;
/*
输入一个字符串，字符串中包含了全量字符集和已占用字符集，两个字符集用@相连。@前的字符集合为全量字符集，@后的字符集为已占用字符集合。已占用字符集中的字符一定是全量字符集中的字符。字符集中的字符跟字符之间使用英文逗号分隔。字符集中的字符表示为字符加数字，字符跟数字使用英文冒号分隔，比如a:1，表示1个a字符。字符只考虑英文字母，区分大小写，数字只考虑正整形，数量不超过100，如果一个字符都没被占用，@标识符仍在，例如a:3,b:5,c:2@

输出描述：
可用字符集。输出带回车换行。

示例1：
输入：a:3,b:5,c:2@a:1,b:2

输出：a:2,b:3,c:2

说明：全量字符集为3个a，5个b，2个c。已占用字符集为1个a，2个b。由于已占用字符集不能再使用，因此，剩余可用字符为2个a，3个b，2个c。因此输出a:2,b:3,c:2。注意，输出的字符顺序要跟输入一致。不能输出b:3,a:2,c:2。如果某个字符已全被占用，不需要输出。例如a:3,b:5,c:2@a:3,b:2，输出为b:3,c:2。
 


可以用linkedHashMap
 */
public class zifuji {
    public static void main(String[] args){
        String s="a:3,b:5,c:2@a:3,b:1";
        zifuji hua=new zifuji();
        String useStr=hua.UserStrings(s);
        System.out.print(useStr);
    }

    public String UserStrings(String s){
        String[] str=s.split("@");
        if(str.length==1){
            return str[0];
        }

        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<str.length;i++){
            map=putmap( map,str[i].split(","),i);
        }

        StringBuffer buf=new StringBuffer();
        for(String key:map.keySet()){
            if(map.get(key)!=0) {
                buf.append(key+":"+map.get(key)+",");
            }
        }
        String s1=buf.toString();
        return s1.substring(0,s1.length()-1);

    }


    public Map<String,Integer> putmap(Map<String,Integer> map,String[] str,int flag){
        for(int i=0;i<str.length;i++){
            String s2=str[i].split(":")[0];
            int a=Integer.parseInt(str[i].split(":")[1]);
            if(flag==1&&map.containsKey(s2)){
                int b=map.get(s2)-a;
                map.put(s2,b);
            }else{
                map.put(s2,a);
            }
        }

        return map;
    }


}
