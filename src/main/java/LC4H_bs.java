public class LC4H_bs {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1.处理nums2为空的情况，只有nums1能为空
        // 2.更容易让绳子到一边的情况，优化速度
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // right = m 和 (m + n + 1) / 2 都是为了保证奇数个落在最中间，向上取整
        while (left <= right) {
            // 通过mid1来控制mid2
            // mid1 + mid2 = (m + n + 1) / 2
            int mid1 = (left + right) / 2;
            int mid2 = (m + n + 1) / 2 - mid1;
            // left1和left2是绳内边缘值，r1和r2是绳外边缘值
            int left1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int right1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
            int left2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int right2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];
            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 0) {
                    // 绳内最大值和绳外最小值 / 2
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    // 找绳内最大值，绳内元素多
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                right = mid1 - 1;
            } else {
                left = mid1 + 1;
            }
        }
        return -1;
    }
}
