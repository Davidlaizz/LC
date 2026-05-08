import java.util.Arrays;

public class LC322_dp {
    // 方法: 动态规划(完全背包)
    //   目标：用最少数量的硬币凑出金额amount
    //   思路：物品是硬币(无限使用)，求凑成amount的最少数量
    //   状态：dp[i]表示凑成金额i的最少硬币数
    //   转移：dp[i] = min(dp[i], dp[i-coin] + 1)
    //   遍历顺序：先物品后背包，背包从左到右(完全背包)
    //   初始化：dp[0]=0，其他为MAX_VALUE表示不可达
    //   复杂度：时间O(n*amount)，空间O(amount)
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
