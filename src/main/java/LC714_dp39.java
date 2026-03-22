public class LC714_dp39 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 0持有 1不持有
        int[] dp = new int[2];
        dp[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], dp[1] - prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i] - fee);
        }
        return dp[1];
    }
}
