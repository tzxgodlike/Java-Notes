package StringProblem;

public class StringMatch {

    public static void main(String[] args) {

        //1.String.indexOf(s,index)方法 从index开始搜索，返回子串在String中出现的起始位置
        String parent = "aaaa";
        int cnt = 0;
        int index = 0;
        String kid = "aa";
        while ((index=parent.indexOf(kid,index))!=-1){
            index = index + 1;
            cnt++;
        }
        System.out.println(cnt);
    }
}
