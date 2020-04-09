import java.util.*;

public class shuziyunsuan{

    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<String>();
        }
        if (num.length() == 1) {
            if (num.equals(target+"")) {
                return Arrays.asList(num);
            }
            return new ArrayList<String>();
        }

        StringBuilder sb = new StringBuilder(num);
        String[] ops = {"+","-",""};
        List<String> ans = new ArrayList<>();
        insertOp(ops,sb,ans,target,1);

        return ans;

    }

    private void insertOp(String[] ops,StringBuilder sb,List<String> ans,int target,int cur) {
        if (cur >= sb.length()) {
            String s = sb.toString();

            int res = calculate(s);
            if (res == target) {
                ans.add(s);
            }
            return ;
        }
        for (int i = 0; i < ops.length; i++) {
            sb.insert(cur,ops[i]);
            if("".equals(ops[i])) { // 不插
                if (cur+1 < sb.length()&&sb.charAt(cur+1) != '0') {
                    insertOp(ops,sb,ans,target,cur + 1);
                }
                if (cur+1 >= sb.length()&& cur > 0 && sb.charAt(cur -1 ) != '0') {
                    String s = sb.toString();
                    int res = calculate(s);

                    if (res == target) {
                        ans.add(s);
                    }
                    return ;
                }
            } else {
                insertOp(ops,sb,ans,target,cur + 2);
                sb.deleteCharAt(cur);
            }

        }
    }
    public int calculate(String s) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> opt = new Stack<>();
        char[] str = s.toCharArray();
        int[] priority = new int[256];
        priority['+'] = 1;
        priority['-'] = 1;

        for (int i = 0; i < str.length;) {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(str[i])) { // 计算出数字
                int n = 0;
                while (i < str.length && Character.isDigit(str[i])) {
                    n = n * 10 + str[i] - '0';
                    i++;
                }

                num.push(n);
            } else if (opt.empty() || priority[opt.peek()] < priority[str[i]]) {  // 符号栈为空，大于等于 栈顶
                opt.push(str[i]);
                i++;
            } else {
                int a = num.empty()? 0:num.pop();
                int b = num.empty()? 0:num.pop();
                num.push(cal(opt.peek(),a,b));
                opt.pop();
            }
        }
        while (!opt.empty()) {
            int a = num.empty()? 0:num.pop();
            int b = num.empty()? 0:num.pop();
            num.push(cal(opt.peek(),a,b));
            opt.pop();
        }
        return num.pop();
    }

    private int cal (char opt,int a,int b) {
        if (opt == '+') {
            return b + a;
        } else if (opt == '-') {
            return b - a;
        }
        return 0;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<Integer> list = new LinkedList<>();
        for (int i = 0;i<t;i++) {
            String s = sc.next();
            int k = sc.nextInt();

            list.add(new shuziyunsuan().addOperators(s,k).size());
        }

        for(int n : list) {
            System.out.println(n);
        }
    }
}