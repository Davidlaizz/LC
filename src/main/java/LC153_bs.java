public class LC153_bs {
    // 方法: 二分查找
    //   目标：在旋转有序数组中找到最小值
    //   思路：根据nums[mid]与nums[right]比较，判断最小值在哪半边
    //   判断逻辑：
    //     若nums[mid] > nums[right]，左半边有序，最小值在右半边(left = mid + 1)
    //     否则，mid可能是最小值，更新min后继续往左找(right = mid - 1)
    //   关键：最小值一定在旋转点，即无序的那半边
    //   终止：循环结束返回min
    //   复杂度：时间O(log n)，空间O(1)
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 最小值一定是循环数组第一个值
            // 判断是否左侧顺序数组，右侧循环数组
            if (nums[mid] > nums[right]) {
                // 左侧是顺序数组[left, mid]
                // min = Math.min(min, nums[left]);
                // 去右侧循环数组找更小值
                left = mid + 1;
            } else {
                // 左侧是循环数组，mid可能是最小，也可能在mid左边
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
        }
        return min;
    }
}
