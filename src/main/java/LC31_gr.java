public class LC31_gr {
    // 方法: 贪心(找规律)
    //   目标：找到字典序下一个排列，若已是最大则返回最小排列
    //   思路：从右找第一个下降点，与右边最小大于它的元素交换，再反转后半部分
    //   过程：
    //     1. 从右找k，使nums[k-1] < nums[k]（第一个下降点）
    //     2. 若k<=0，整个数组递减，已是最大排列，直接反转
    //     3. 否则，从右找t，使nums[t] > nums[k-1]，交换
    //     4. 反转[k, n-1]，使后半部分变升序（最小）
    //   复杂度：时间O(n)，空间O(1)
    public void nextPermutation(int[] nums) {
        int k = nums.length - 1;
        while (k > 0 && nums[k - 1] >= nums[k]) {
            k--;
        }

        if (k <= 0) {
            reverse(nums, 0, nums.length - 1);
        } else {
            int t = nums.length - 1;
            while (nums[t] <= nums[k - 1]) {
                t--;
            }

            swap(nums, t, k - 1);

            reverse(nums, k, nums.length - 1);
        }
    }

    public void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
