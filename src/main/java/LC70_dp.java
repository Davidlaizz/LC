public class LC70_dp {
    // 方法: 动态规划
    //   目标：爬n阶楼梯，每次可爬1或2阶，求方法数
    //   状态：dp[i]表示爬到第i阶的方法数
    //   转移：dp[i] = dp[i-1] + dp[i-2]（从i-1爬1阶或从i-2爬2阶）
    //   初始化：dp[0]=1, dp[1]=1
    //   复杂度：时间O(n)，空间O(n)，可压缩到O(1)
    public int climbStairs(int n) {
        if (n == 0) return 0;
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
