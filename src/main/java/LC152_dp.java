public class LC152_dp {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int maxDp[] = new int[n];
        int minDp[] = new int[n]; //存储负数，负负得正
        int max = nums[0];
        maxDp[0] = nums[0]; //0~i-1能够乘积的最大值
        minDp[0] = nums[0];
        for(int i = 1; i < n; i++){
            maxDp[i] = Math.max(Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]), nums[i]);
            max = Math.max(maxDp[i], max);
            //存储负数，负负得正
            minDp[i] = Math.min(Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]), nums[i]);
        }
        return max;
    }
}
