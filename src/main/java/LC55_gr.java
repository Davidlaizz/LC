public class LC55_gr {
    // 方法: 贪心
    //   目标：判断能否从起点跳到终点
    //   思路：维护当前能到达的最远位置step，若能覆盖终点则成功
    //   过程：遍历每个位置，更新step = max(step, i + nums[i])
    //   终止：step >= n-1 返回true，i超过step返回false
    //   关键：i <= step 保证当前位置可达，否则无法继续
    //   复杂度：时间O(n)，空间O(1)
    public boolean canJump(int[] nums) {
        int step = 0;
        int i = 0;
        while (i <= step && i < nums.length) {
            step = Math.max(nums[i] + i, step);
            i++;
            if (step >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
