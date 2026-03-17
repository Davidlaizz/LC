public class LC518_dp19 {
    public int change(int amount, int[] coins) {
        // 完全背包:：每种物品有无限个
        int dp[] = new int[amount + 1];
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++){
            // 关键：从左往右遍历
            for(int j = coins[i]; j <= amount; j++){
                // 组合数是先物品后背包，保证物品顺序和遍历顺序一致，即使物品重复也保证一种选择
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
