public class LC188H_dp36 {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        k = Math.min(k, len / 2); // 因为n天最多只能进行n/2次买卖,否则无意义,比如5天只支持2次买卖
        int[][] buy = new int[len][k + 1]; // 表示[0, i]天中买第i次的最大值
        int[][] sell = new int[len][k + 1];
        for (int j = 0; j <= k; j++) {
            buy[0][j] = -prices[0]; // 第 0 天买入第 j 次股票的状态
        }
        for(int i = 1; i < len; i++){
            for(int j = 1; j <= k; j++){
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j - 1] - prices[i]); //i天买k次卖k-1次
                sell[i][j] = Math.max(sell[i - 1][j], buy[i][j] + prices[i]);       //i天买卖k次
            }
        }
        return sell[len - 1][k];
    }

    // 状态压缩 天数不重要
    public int maxProfit2(int k, int[] prices) {
        int len = prices.length;
        k = Math.min(k, len / 2); //因为n天最多只能进行n/2次买卖,否则无意义,比如5天只支持2次买卖
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        for (int j = 0; j <= k; j++) {
            buy[j] = -prices[0]; // 第 0 天买入第 j 次股票的状态
        }
        for(int i = 1; i < len; i++){
            for(int j = 1; j <= k; j++){
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]); //i天买k次卖k-1次
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);       //i天买卖k次
            }
        }
        return sell[k];
    }
}
