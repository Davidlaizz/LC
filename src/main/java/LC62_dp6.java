import java.util.Arrays;

public class LC62_dp6 {
    public int uniquePaths(int m, int n) {
        // 在二维dp数组中，当前值的计算只依赖正上方和正左方，因此可以压缩成一维数组。
        // 状态压缩,把m行压缩成一行
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) { // 第0行是纯1,已初始化
            for (int j = 1; j < n; j++) { // 第0列是纯1
                // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                dp[j] += dp[j - 1]; // 每一行都是纯1,dp[j]本身就是从上一行传下来的,只要再添加d[j - 1]
            }
        }
        return dp[n - 1];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            dp[i][1] = 1;
        }
        for(int i = 1; i <= n; i++){
            dp[1][i] = 1;
        }
        for(int i = 2; i <= m; i++){
            for(int j = 2; j <= n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }
}
