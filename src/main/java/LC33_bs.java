public class LC33_bs {
    // 方法: 二分查找
    //   目标：在旋转有序数组中搜索目标值
    //   思路：根据nums[mid]与nums[right]比较，判断哪半边是有序的
    //   判断逻辑：若nums[mid] > nums[right]，左半边有序，否则右半边有序
    //   有序区间判断：target在有序区间内则缩小到该区间，否则去另一边
    //   终止：找到target返回mid，否则返回-1
    //   复杂度：时间O(log n)，空间O(1)
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ( right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 判断是否是左侧顺序数组，右侧循环数组
            if (nums[mid] > nums[right]) {
                // 左侧顺序数组
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右侧顺序数组
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
