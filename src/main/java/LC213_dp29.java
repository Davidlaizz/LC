public class LC213_dp29 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        // 首尾相连说明有两种情况：不选头或不选尾
        int res1 = robAction(nums, 0, n - 2);
        int res2 = robAction(nums, 1, n - 1);
        return Math.max(res1, res2);
    }
    // [start, end]
    public int robAction(int[] nums, int start, int end) {
        if (start > end)
            return 0;
        int n = end - start + 1;
        if (n == 1)
            return nums[start];
        int[] dp = new int[2];
        dp[0] = nums[start]; // -2天
        dp[1] = Math.max(nums[start], nums[start + 1]); // - 1天
        int newDay = 0;
        for (int i = start + 2; i <= end; i++){
            newDay = Math.max(dp[1], dp[0] + nums[i]);
            // 更新
            dp[0] = dp[1];
            dp[1] = newDay;
        }
        return dp[1];
    }
}
