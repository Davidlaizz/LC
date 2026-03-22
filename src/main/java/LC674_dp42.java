import java.util.Arrays;

public class LC674_dp42 {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                dp[i] = dp[i - 1] + 1;
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    // 状态压缩
    public int findLengthOfLCIS2(int[] nums) {
        int n = nums.length;
        // 前一个元素和当前元素的最大长度
        int[] dp = new int[2];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                dp[1] = dp[0] + 1;
                res = Math.max(res, dp[1]);
                // 为了下一轮而更新
                dp[0] = dp[1];
            } else {
                dp[0] = 1;
            }
        }
        return res;
    }
}
