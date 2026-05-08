import java.util.Arrays;

public class LC279_dp24 {
    // 方法: 动态规划(完全背包)
    //   目标：找到和为n的最少完全平方数数量
    //   思路：物品是完全平方数(1,4,9,...)，求凑成n的最少数量
    //   状态：dp[j]表示和为j的最少完全平方数数量
    //   转移：dp[j] = min(dp[j], dp[j-i*i] + 1)
    //   遍历顺序：先物品后背包，背包从左到右(完全背包)
    //   复杂度：时间O(n√n)，空间O(n)
    public int numSquares(int n) {
        // 物品是平方数
        // 完全背包：物品数无限,从左往右遍历
        // 组合数: 先物品后背包
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= Math.sqrt(n); i++) {
            for (int j = i * i; j <= n; j++) {
                // 平方和组成不到j - i * i， 也就没意义继续了
                if (dp[j - i * i] == Integer.MAX_VALUE)
                    continue;
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }
}
