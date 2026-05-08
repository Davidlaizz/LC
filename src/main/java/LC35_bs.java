public class LC35_bs {
    // 方法: 二分查找
    //   目标：在有序数组中找到目标值，若不存在则返回应插入的位置
    //   思路：标准二分查找，循环结束时left即为插入位置
    //   关键：while(left <= right)退出时，left指向第一个大于target的位置
    //   终止：找到target返回mid，否则返回left
    //   复杂度：时间O(log n)，空间O(1)
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}
