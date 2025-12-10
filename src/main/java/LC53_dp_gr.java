public class LC53_dp_gr {
    public int maxSubArray(int[] nums) {
        // int n = nums.length;
        // int dp[] = new int[n];
        // dp[0] = nums[0];
        // int res = nums[0];
        // for(int i = 1; i < n; i++){
        //     dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        //     res = Math.max(res, dp[i]);
        //     // System.out.println(dp[i]);
        // }
        // return res;

        // 贪心
        int res = nums[0];
        int cnt = 0;
        for(int x : nums){
            cnt += x;
            if(cnt > res){
                res = cnt;
            }
            if(cnt < 0){
                cnt = 0;
            }
        }
        return res;
    }
}
