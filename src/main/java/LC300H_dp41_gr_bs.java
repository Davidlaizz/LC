import java.util.Arrays;

public class LC300H_dp41_gr_bs {
    // 方法1: 动态规划
    //   目标：求最长严格递增子序列长度
    //   状态：dp[i]表示以nums[i]结尾的最长递增子序列长度
    //   转移：遍历j<i，若nums[j]<nums[i]，dp[i]=max(dp[i], dp[j]+1)
    //   复杂度：时间O(n²)，空间O(n)
    //
    // 方法2: 贪心+二分
    //   思路：维护数组dp，dp[index]表示长度为index+1的递增子序列的最小末尾
    //   过程：若nums[i]>dp[index]，直接追加；否则二分找第一个>=nums[i]的位置替换
    //   原理：末尾越小，越容易接上后续元素
    //   复杂度：时间O(n log n)，空间O(n)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        // [0, i]下标对应的最长长度
        Arrays.fill(dp, 1); // 至少有自己
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) //在dp[j]后追加一个nums[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        // [0, index]下标对应的最后一位元素的大小
        // 贪心：最后一位尽可能小(方便后面元素追加)，用二分查找去找
        int index = 0;
        dp[index] = nums[0];
        for (int i = 1; i < n; i++) {
            if (dp[index] < nums[i]) {
                // 符合条件直接追加
                index++;
                dp[index] = nums[i];
            } else {
                // nums[i]更小，用二分去找第一个大于他nums[i]的位置，替换 参考LC35
                int l = 0, r = index;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (dp[mid] < nums[i]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                // l就是第一个大于的位置
                dp[l] = nums[i];
            }
        }
        return index + 1;
    }
}
