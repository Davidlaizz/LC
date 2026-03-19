import java.util.Arrays;

public class LC322_dp {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 关键
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; //根据示例3
        // 凑到i的最小数是dp[i]
        // 完全背包: 遍历背包的时候从左往右
        // 组合数：先物品再背包
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                } else {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
