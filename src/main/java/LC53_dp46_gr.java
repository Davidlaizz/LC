public class LC53_dp46_gr {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; //i下标下对应的最大连续和
        int res = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // 舍弃<0的dp[i - 1]
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        // 贪心
        int res = nums[0];
        int cnt = 0;
        for(int x : nums){
            cnt += x;
            if(cnt > res){
                res = cnt;
            }
            if(cnt < 0){ //舍弃<0的情况
                cnt = 0;
            }
        }
        return res;
    }
}
