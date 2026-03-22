public class LC122_dp4 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 第i天持有或不持有股票的最大利润
        int[] dp = new int[2];
        dp[0] = -prices[0]; // 持有
        dp[1] = 0; // 不持有
        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], dp[1] - prices[i]); // 关键： dp[1] - prices[i]，dp[1]可能已经盈利了
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
        }
        return dp[1];
    }
}
