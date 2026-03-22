public class LC718_dp43 {
    public int findLength(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int res = 0;
        int[][] dp = new int[n1 + 1][n2 + 1];
        // 表示以i-1为尾的nums1和以j-1为尾的nums2
        // 如果以i为尾要对dp[i][0] dp[0][j]初始化
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    // 滚动数组：在当前外层循环遍历到 nums1 的第 i 个元素时，dp[j] 表示以当前的 nums1[i - 1] 和 nums2[j - 1] 结尾的最长重复子数组的长度。
    // 可以把它想象成二维数组中的“一行”。外层循环每递增一次 i，这个一维数组就会原地刷新一次，从“第 i-1 行的状态”变成“第 i 行的状态”。
    public int findLength2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int res = 0;
        int[] dp = new int[n2 + 1];
        // 表示以i-1为尾的nums1和以j-1为尾的nums2
        // 如果以i为尾要对dp[i][0] dp[0][j]初始化
        for (int i = 1; i <= n1; i++) {
            for (int j = n2; j >= 1 ; j--) { // 防止重复选择，01背包
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    res = Math.max(res, dp[j]);
                } else {
                    // 刷新掉dp[i - 1][j] 的历史数据
                    dp[j] = 0;
                }
            }
        }
        return res;
    }

    // 第二种方法改成正向遍历
    public int findLength3(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int res = 0;
        int[] dp = new int[n2 + 1];

        for (int i = 1; i <= n1; i++) {
            // 进入新的一行，初始化 pre 为左上角边界 dp[i-1][0]，始终为 0
            int pre = dp[0];

            // 【看这里：改成了正向遍历】
            for (int j = 1; j <= n2; j++) {
                // 1. 赶紧把正上方的值（旧 dp[j]）备份到 cur 中
                int cur = dp[j]; // 此时是dp[i - 1][j - 1]

                if (nums1[i - 1] == nums2[j - 1]) {
                    // 2. 匹配时，使用被我们抢救下来的左上角 pre
                    dp[j] = pre + 1;
                    res = Math.max(res, dp[j]);
                } else {
                    // 不匹配时，毫不留情地清零，防止脏数据污染
                    dp[j] = 0;
                }

                // 3. 收尾交接：刚刚备份的正上方 cur，交给 pre 去做下一轮的左上角
                pre = cur;
            }
        }
        return res;
    }

}
