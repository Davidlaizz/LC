public class LC153_bs {
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
