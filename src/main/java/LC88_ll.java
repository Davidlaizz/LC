public class LC88_ll {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 逆向双指针，从后往前遍历，先放大的值,原地合并
        int tail = m + n - 1;
        int t1 = m - 1;
        int t2 = n - 1;
        int cur;
        while (t1 >= 0 || t2 >= 0) {
            if (t1 == -1) {
                cur = nums2[t2--];
            } else if (t2 == -1) {
                cur = nums1[t1--];
            } else if (nums1[t1] > nums2[t2]) {
                cur = nums1[t1--];
            } else {
                cur = nums2[t2--];
            }
            nums1[tail--] = cur;
        }
    }
}
