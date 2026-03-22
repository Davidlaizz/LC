public class LC121_dp32_gr {
    public int maxProfit(int[] prices) {
         // 贪心
         int low = prices[0];
         int profit = 0;
         for(int x : prices){
             low = Math.min(low, x); //找最低价
             profit = Math.max(profit, x - low); //找最大利润
         }
         return profit;
    }

    // dp
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        // 第i天持有或不持有股票的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0]; // 持有
        dp[0][1] = 0; // 不持有
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], - prices[i]); // 只能持有+卖掉一次
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[n - 1][1];
    }

    // dp 状态压缩
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        // 第i天持有或不持有股票的最大利润
        int[] dp = new int[2];
        dp[0] = -prices[0]; // 持有
        dp[1] = 0; // 不持有
        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], - prices[i]); // 只能持有+卖掉一次
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
        }
        return dp[1];
    }
}
