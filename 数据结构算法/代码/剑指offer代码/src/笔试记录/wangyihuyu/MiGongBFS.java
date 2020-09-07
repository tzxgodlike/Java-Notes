package 笔试记录.wangyihuyu;

import java.util.*;

public class MiGongBFS {
    public static HashMap<String,Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for (int i = 0;i<times;i++) {

            int n = sc.nextInt();
            map.put(n+"#"+n,0);
            int x = n,y = n;
            for (int j = 0;j<n;j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                if (b==-1) continue;
                if (a==0) {
                    y++;
                }else if (a==1) {
                    y--;
                }else if (a==2) {
                    x--;
                }else if (a==3) {
                    x++;
                }
                map.put(x+"#"+y,0);
            }
            int[][] visited = new int[2*n+1][2*n+1];
            for (int q =0;q<2*n+1;q++){
                Arrays.fill(visited[q],0);
            }
            System.out.println(bfs(n,n,x,y,visited));
            map.clear();
        }
    }

    private static int bfs(int startX, int startY, int endX, int endY,int[][] visited) {
        Queue<String> queue = new LinkedList<>();
        int ans  = -1;
        queue.offer(startX+"#"+startY);
        map.put(startX+"#"+startY,1);
        while (!queue.isEmpty()) {
            ans++;
            for (int i = 0;i<queue.size();i++) {
                String tmp = queue.poll();
                if (tmp.equals(endX+"#"+endY)) {
                    //System.out.println(ans);
                    return ans;
                }
                String[] s = tmp.split("#");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                String right = x+"#"+(y+1);
                String left = x+"#"+(y-1);
                String up = (x-1)+"#"+y;
                String down = (x+1)+"#"+y;
                if (map.containsKey(right)&&map.get(right)!=1) {
                    queue.offer(right);
                    map.put(right,1);
                }
                if (map.containsKey(left)&&map.get(left)!=1) {
                    queue.offer(left);

                    map.put(left,1);
                }
                if (map.containsKey(up)&&map.get(up)!=1) {
                    queue.offer(up);
                    map.put(up,1);
                }
                if (map.containsKey(down)&&map.get(down)!=1) {
                    queue.offer(down);
                    map.put(down,1);
                }
            }
        }
        return -1;
    }
}
