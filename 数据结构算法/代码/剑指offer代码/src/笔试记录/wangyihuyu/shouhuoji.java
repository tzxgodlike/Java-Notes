package 笔试记录.wangyihuyu;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;
        import java.util.Stack;

public class shouhuoji {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //n是货物槽的数量
        int m = sc.nextInt();  //m是操作的人数
        List<Stack<Integer>> stacks = new ArrayList<>();

        int[][] hand = new int[m][2];  //手  0 左手  1右手
        List<List<Integer>> bags = new ArrayList<>();   //包里
        for(int i = 0;i<m;i++) {
            bags.add(new ArrayList<Integer>());
        }
        for(int i = 0;i<n;i++) {
            stacks.add(new Stack<Integer>());
        }
        for (int i = 0;i<n;i++) {
            int danjia = sc.nextInt();
            for (int j = 0;j<100;j++) {
                stacks.get(i).push(danjia);
            }
        }
        for (int i = 0;i<m;i++) {   //i代表第i个人
            int k = sc.nextInt();
            sc.nextLine();
            for (int j = 0;j<k;j++) {
                String[] s = sc.nextLine().split(" ");
                if (s[1].equals("take")) {   //拿东西
                    //槽编号
                    int no = Integer.parseInt(s[2])-1;

                    if (s[0].equals("left")) {
                        //拿的货物槽
                        hand[i][0] = stacks.get(no).pop();

                    }else  if (s[0].equals("right")){
                        //右手拿
                        hand[i][1] = stacks.get(no).pop();
                    }
                }else if (s[1].equals("return")) {  //放回货物槽
                    int no = Integer.parseInt(s[2])-1;
                    if (s[0].equals("left")) {
                        stacks.get(no).push(hand[i][0]);
                        hand[i][0] = 0;
                    }else if (s[0].equals("right")){
                        stacks.get(no).push(hand[i][1]);
                        hand[i][1] = 0;
                    }
                } else if (s[1].equals("keep")) {  //放包里

                    if (s[0].equals("left")) {
                        bags.get(i).add(hand[i][0]);
                        hand[i][0] = 0;
                    }else if (s[0].equals("right")) {
                        bags.get(i).add(hand[i][1]);
                        hand[i][1] = 0;
                    }
                }
            }
        }

        //m个人遍历完毕
        List<Integer> res = new ArrayList<>();
        for (int i = 0;i<m;i++) {
            int ans = hand[i][0]+hand[i][1];
            for (int tmp:bags.get(i)) {
                ans = ans+tmp;
            }
            System.out.println(ans);
            res.add(ans);
        }

    }
}
