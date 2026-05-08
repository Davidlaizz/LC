import java.util.Arrays;

public class LC416_dp13_dfsmemo {
    // 方法1: 动态规划(0-1背包)
    //   目标：判断数组能否分割成两个和相等的子集
    //   思路：转化为0-1背包问题，能否选出元素使和为sum/2
    //   状态：dp[j]表示能否凑出和j
    //   转移：dp[j] = max(dp[j], dp[j-nums[i]] + nums[i])
    //   遍历顺序：先物品后背包，背包从右到左(0-1背包)
    //   复杂度：时间O(n*s)，空间O(s)，s=sum/2
    //
    // 方法2: 记忆化DFS
    //   思路：从index开始，选择或不选择当前元素，凑出remain
    //   记忆化：memo[index][remain]记录该状态是否可解
    //   剪枝：若已计算过直接返回，避免重复计算
    //   复杂度：时间O(n*s)，空间O(n*s)
    // 两种写法都是
    // 时间复杂度：O(ns)，其中 n 是 nums 的长度，s 是 nums 的元素和（的一半）。
    // 空间复杂度：O(ns)。 dp状态压缩后是O(s)
    // 更进阶：把布尔数组压缩成一个二进制数，二进制数从低到高第 i 位是 0，表示布尔数组的第 i 个元素是 false；从低到高第 i 位是 1，表示布尔数组的第 i 个元素是 true

    // 本题，相当于背包里放入数值，那么物品i的重量是nums[i]，其价值也是nums[i]。
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1)
            return false;
        int n = nums.length;
        int dp[] = new int[sum / 2 + 1]; // 一半就行
        for (int i = 0; i < n; i++) {
            for (int j = sum / 2; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        // 判断重sum / 2的背包是否能装sum / 2价值
        return dp[sum / 2] == sum / 2;
    }

    // 使用记忆化dfs
    /*
     * 记忆化数组
     * memo[index][remain] 表示：
     * 从 index 开始的元素中，是否可以凑出 remain
     *
     * 三种状态：
     * null  -> 没有计算过
     * true  -> 可以凑出来，凑出来就直接一路返回了
     * false -> 凑不出来，关键就是靠他剪枝
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1)
            return false;
        // 只要刚好能找出一半即可
        int target = sum / 2;
        // 默认是null
        Boolean[][] memo = new Boolean[nums.length][target + 1];
        return dfs(nums, 0, target, memo);
    }
    // index表示递归到的下标,总体还有remain
    public boolean dfs(int[] nums, int index, int remain, Boolean[][] memo) {
        // 终止条件
        if (remain == 0)
            return true;
        // 边界判断
        if (remain < 0 || index == nums.length)
            return false;
        // 剪枝 记忆化
        // 如果已经有记忆了,直接返回
        if (memo[index][remain] != null)
            return memo[index][remain];
        // 选择当前元素,递归寻找答案
        if (dfs(nums, index + 1, remain - nums[index], memo)) { //如果找到答案
            // memo[index][remain] = true; // 没必要了,一路返回
            return true;
        }
        // 不选择当前元素
        else {
            boolean res = dfs(nums, index + 1, remain, memo);
            memo[index][remain] = res;
            return res;
        }
    }
}
