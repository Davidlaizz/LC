import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC494_dp16_dfsmemo {
    public int findTargetSumWays(int[] nums, int target) {
        // 隐藏的01背包问题
        // 转成left（正数）- right(负数) = target
        // left + right = sum => left = (sum + target) / 2
        // left是正数和 不可能有小数，所以(sum + target)一定是偶数
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum)
            return 0;
        if ((sum + target) % 2 == 1)
            return 0;
        int left = (sum + target) / 2;
        int[] dp = new int[left + 1]; // 构成i数有dp[i]种组合
        dp[0] = 1;
        // 组合数：先遍历物品再遍历背包，遍历物品的顺序就是拿的顺序，不会反复拿
        for (int num : nums) {
            for (int i = left; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[left];
    }

    // 时间复杂度：O(n * sum)，其中 n 是数组的长度，sum 是数组元素的绝对值之和(最坏情况)。
    // 因为我们需要遍历所有的状态 (u, cur)，每个状态的计算时间是常数 O(1)。
    // 关键点在于 我们不再关心每种组合的具体路径，而是关注每个状态 (u, cur) 是否被计算过。
    // 空间复杂度：O(n * sum)，主要由哈希表 cache 存储每个状态的计算结果决定。
    public int findTargetSumWays3(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }
    // 也可以是数组存储memo[index][sum / 2] = 组合数,
    // 因为加减互斥，从sum / 2开始枚举，一个-num一个不变, 结果为0则满足
    Map<String, Integer> memo = new HashMap<>();
    public int dfs(int[] nums, int target, int index, int curSum) {
        int n = nums.length;
        String key = index + "_" + curSum;
        if (index == n) {
            if (target == curSum){
                memo.put(key, 1);
                return 1;
            }
            else{
                memo.put(key, 0);
                return 0;
            }
        }
        // 查看是否重复,剪枝
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int left = dfs(nums, target, index + 1, curSum + nums[index]);
        int right = dfs(nums, target, index + 1, curSum  - nums[index]);
        // 记忆化
        memo.put(key, left + right);
        return left + right;
    }

    // 普通暴力dfs 2^n
}
