public class LC162H_bs {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 0;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 边界判断
            if ((mid == 0 && nums[mid] > nums[mid + 1]) || (mid == n - 1 && nums[mid] > nums[mid - 1]))
                return mid;
            // 判断峰值位置
            if (nums[mid] < nums[mid + 1]) {
                // 在右侧
                left = mid + 1;
            }else if (nums[mid - 1] > nums[mid]) {
                // 在左侧
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
