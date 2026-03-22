public class LC198_dp30 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) // 直接偷
            return nums[0];
        // 关键：[0, i]下标的物品中能拿的最大值为dp[i]
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    // 状态压缩
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) // 直接偷
            return nums[0];
        // 压缩：只需要记录前两次状态即可,迭代更新状态时还要一个变量座位temp
        int[] dp = new int[2];
        dp[0] = nums[0]; // [0, i - 2]下标的最大值
        dp[1] = Math.max(nums[0], nums[1]); // 第[0, i - 1]下标的最大值
        int newDay = 0; // 最新的一天的最大值 （n = 2时不会进迭代，所以是return dp[1]而不是newDay）
        for (int i = 2; i < n; i++) {
            newDay = Math.max(dp[1], dp[0] + nums[i]);
            // 更新为新一次迭代准备
            dp[0] = dp[1];
            dp[1] = newDay;
        }
        return dp[1];
    }
}
