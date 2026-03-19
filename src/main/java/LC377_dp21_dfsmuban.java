import java.util.Arrays;

public class LC377_dp21_dfsmuban {
    // dp和dfs在 DFS 中，
    // 算了 dfs(i) 的结果，为了下次不再重复算，你把它存进了 memo[i]。
    // 在 DP 中，你通过前面的状态推导出了i的结果，你把它存进了 dp[i]。
    // 在这个时刻，memo[i] 和 dp[i] 里面的数字是绝对相等的，代表的物理意义也完全一样。
    // 时间空间复杂度相同 O（n*target） O（target）
    // memo 是按需填写、跳跃式填写。DFS 走到哪里，哪里才会被填上数字。如果有些状态根本走不到，memo 里对应的格子永远是初始值（比如 -1）。
    // dp 是地毯式搜索、顺序填写。写了 for 循环，就得老老实实从头填到尾，一行一行、一格一格地填满。
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // 物品中满足i有dp[i]种
        // 排列数：先遍历背包再遍历物品
        // 完全背包：可重复选择 从左往右
        for (int i = 0; i <= target; i++) { // 从左往右遍历背包
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j])
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }

    public int combinationSum42(int[] nums, int target) {
        // 也是和dp[]含义一样
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        // 从第 0 阶开始爬
        return dfs(0, nums, target, memo);
    }
    // curr 表示当前爬到的台阶数
    private int dfs(int curr, int[] nums, int target, int[] memo) {
        if (curr == target) { // 刚好爬到目标
            return 1;
        }
        if (memo[curr] != -1) { // 之前计算过从 curr 爬到 target 的方案数
            return memo[curr];
        }

        int res = 0;
        // 关键：这个循环保证了是排列数，否则是组合数
        for (int x : nums) {
            if (curr + x <= target) { // 往上爬，不能超过 target
                res += dfs(curr + x, nums, target, memo);
            }
        }
        return memo[curr] = res; // 记忆化：记录从 curr 爬到顶的方案数
    }
}
