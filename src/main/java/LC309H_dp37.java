public class LC309H_dp37 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 持股[i][0] 卖出冻结[i][1] 不持有不冻结[i][2]
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for(int i = 1; i < n; i++){
            // 和前一天相关，顺序可换
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][2], f[i - 1][1]); // 今天解冻
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }

    // 状态压缩，根据第一种算法优化了天数，会增加临时变量（硬想很难想到临时变量）
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        // 持股[i][0] 卖出冻结[i][1] 不持有不冻结[i][2]
        int[] f = new int[3];
        f[0] = -prices[0];
        for(int i = 1; i < n; i++){
            int lastHold = f[0];
            int lastFrozen = f[1];
            f[0] = Math.max(lastHold, f[2] - prices[i]);
            f[1] = lastHold + prices[i];
            f[2] = Math.max(lastFrozen, f[2]);
        }
        return Math.max(f[1], f[2]);
    }
}
