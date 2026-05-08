public class LC45H_gr {
    // 方法: 贪心
    //   目标：从起点跳到终点的最小跳跃次数
    //   思路：维护当前步能到达的边界cur，和下一步能到达的最远位置next
    //   过程：遍历数组，持续更新next = max(next, i + nums[i])
    //         当i到达cur边界时，跳跃次数+1，cur = next
    //   关键：在当前步范围内尽可能走远，到达边界才跳跃
    //   终止：cur >= n-1 时已能到达终点
    //   复杂度：时间O(n)，空间O(1)
    public int jump(int[] nums) {
        int next = 0;
        int i = 0;
        int times = 0;
        int cur = 0;
        if (nums.length == 1) {
            return 0;
        }
        while (i <= next && i < nums.length - 1) {
            next = Math.max(nums[i] + i, next);
            if (i == cur) {
                times++;
                cur = next;
                if (cur >= nums.length - 1) {
                    break;
                }
            }
            i++;
        }
        return times;
    }
}
