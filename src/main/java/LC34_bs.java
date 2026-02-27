public class LC34_bs {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int idx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                idx = mid;
                break;
            }
        }
        if (idx == -1)
            return new int[]{-1, -1};
        int i = idx, j = idx;
        // 找第一和最后一个
        while (i >= 0 && nums[i] == target) {
            i--;
        }
        while (j <= nums.length - 1 && nums[j] == target) {
            j++;
        }
        return new int[]{i + 1, j - 1};
    }

    public int[] searchRange2(int[] nums, int target) {
        return new int[]{binarySearch(nums, target, true), binarySearch(nums, target, false)};
    }
    public int binarySearch(int[] nums, int target, boolean first){
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (first == true) {
                    if (mid == 0 || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        right =  mid - 1;
                    }
                } else {
                    if (mid == nums.length - 1 || nums[mid + 1] != target){
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
        return -1;
    }
}
