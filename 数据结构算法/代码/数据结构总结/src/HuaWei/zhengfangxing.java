package HuaWei;

public class zhengfangxing {

    /*
    输入一个二维01矩阵，判断矩阵中全为1的正方形的最大边长


    将矩阵中每个点作为正方形右下角来处理，然后该点比它的左方，上方，左上方为右下角的正方形边长多1， 因此，只需要从这三个正方形中取最小的正方形边长， 然后+1 作为当前正方形的边长， 

状态转移方程：

       dp[i][j] = min(dp[i-1][j-1], min(dp[i][j-1], dp[i-1][j])) + 1

     */

    /*

    class Solution(object):

    def getmax(self, lists):
        if lists == [] or not lists:
            return 0

        row = len(lists)
        col = len(lists[0])
        res = 0
        dp = [[0 for i in range(col)] for j in range(row)]
        print(dp)
        for i in range(row):
            for j in range(col):
                if i == 0 or j == 0:
                    if lists[i][j] == 1:
                        dp[i][j] = 1
                        res = 1

        for i in range(row):
            for j in range(col):
                if lists[i][j] == 1:
                    mins = min(dp[i-1][j], dp[i][j-1])
                    dp[i][j] = min(dp[i-1][j-1], mins) + 1

                res = max(res, dp[i][j])

        return res

c++
class Solution {
public:
    int maximalSquare(vector<vector<char>> &matrix) {
        if (matrix.size() == 0) {
            return 0;
        }
        if (matrix[0].size() == 0) {
            return 0;
        }
        int m = matrix.size();
        int n = matrix[0].size();
        int ans = 0;

        vector<vector<int>> dp(m, vector<int>(n, 0));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0')
                    dp[i][j] = 0;
                else  {
                    dp[i][j] = 1;
                    if (i > 0 && j > 0) {
                        dp[i][j] += min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1]));
                    }
                    if (dp[i][j] > ans) {
                        ans = dp[i][j];
                    }
                }
            }
        }
        return ans * ans;
    }
};

     */

}
