public class LC123H_dp35 {
    // 类似LC188
    public int maxProfit(int[] prices) {
        // 一天一共就有五个状态，
        // 没有操作不持有（也可以不设置这个状态）
        // 第一次持有股票
        // 第一次不持有股票
        // 第二次持有股票
        // 第二次不持有股票
        int n = prices.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][5];
        // 第0天
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0]; //第一天买卖买的情况
        for (int i = 1; i < n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // dp[i - 1][0]=0没意义可省略
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[n - 1][4]; // 即使只有一次买卖盈利，也会再空买卖一次转移到dp[4]
    }



    // 状态压缩 且省略不持有的状态（反正是0）
    public int maxProfit2(int[] prices) {
        int buy1 = -prices[0], buy2 = -prices[0]; //buy2是初始化买->卖->买prices[0]的情况
        int sell1 = 0, sell2 = 0;
        for(int i = 0; i < prices.length; i++){
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2; // 即使只有一次买卖盈利，也会再空买卖一次转移到sell2
    }
}
