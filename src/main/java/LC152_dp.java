public class LC152_dp {
    // 方法: 动态规划
    //   目标：求乘积最大的非空连续子数组
    //   思路：维护最大值和最小值，负数可能翻转成最大
    //   状态：maxDp[i]表示以i结尾的最大乘积，minDp[i]表示最小乘积
    //   转移：maxDp[i]=max(maxDp[i-1]*nums[i], minDp[i-1]*nums[i], nums[i])
    //         minDp[i]=min(maxDp[i-1]*nums[i], minDp[i-1]*nums[i], nums[i])
    //   关键：负负得正，所以要同时维护最小值
    //   复杂度：时间O(n)，空间O(n)，可压缩到O(1)
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int[] maxDp = new int[n];
        int[] minDp = new int[n]; //存储负数，负负得正
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
