package Chapter6;

public class DicesProbability_60 {
    /*
    把N个骰子扔在地上，所有骰子朝上一面的点数之和为s 输入n 打印出s所有可能的值得出现的概率
     */

    private static int diceNum = 6;
    /*
    递归
     */

    public static void printProbability(int n){
        if (n<1) return ;
        int maxVal = n*diceNum; //点数和的最大值为maxVal  最小值为n

        int[] probabilities = new int[maxVal-n+1];

        getProbabilities(n, n, 0, probabilities);
        
        int total = (int)Math.pow(diceNum,n);
        for (int i = n; i <= maxVal; i++) {
            System.out.println("s=" + i + ": " + probabilities[i - n] + "/" + total);
        }

    }

    private static void getProbabilities(int n, int cur, int sum, int[] p) {

        //n是用来确定p的index cur是用来表示扔第几个骰子
        if (cur==0) {
            p[sum-n]++;
            return ;
        }
        for (int i = 1;i<=diceNum;i++) {
            getProbabilities(n, cur - 1, sum + i, p);
        }
    }

    /*
    DP
     */
    public static void printProbabilityDP(int n){
        //F(n, s) 表示n个骰子点数和为s的种数，n表示骰子个数，s表示n个骰子的点数和
        //F(n, s) = F(n-1, s-1) + F(n-1, s-2) + F(n-1, s-3) + F(n-1, s-4) + F(n-1, s-5) + F(n-1, s-6)
        if (n < 1) return;
        int maxVal = n * diceNum;
        int[][] f = new int[n + 1][maxVal + 1];

        // 初始化f(1, i) 1 <= i <= 6, 只有一个骰子，点数和为i的情况只有一种
        for (int i = 1; i <= diceNum; i++) {
            f[1][i] = 1;
        }

        // 逐个增加骰子个数

        for (int k = 2;k<=n;k++) {
            //k个骰子和数为k~6k
            for (int sum = k;sum<=diceNum*k;sum++) {
                //状态转移公式
                for (int i = 1; sum > i && i <= diceNum; i++) {
                    f[k][sum] += f[k - 1][sum - i];
                }
            }
        }


        int total = (int) Math.pow(diceNum, n);
        for (int sum = n; sum <= maxVal; sum++) {
            System.out.println("s=" + sum + ": " + f[n][sum] + "/" + total);
        }
    }

    public static void main(String[] args) {
        //printProbability(6);
        printProbabilityDP(6);
    }
}
