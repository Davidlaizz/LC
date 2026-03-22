public class LC1143_dp44 {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1]; //以i - 1, j - 1为尾的最大长度
        int res = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                } else { // 不要求连续，继承以前的最大值就好
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return res;
    }

    // 压缩了n1 建议参考第一种方法
    public int longestCommonSubsequence2(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        // 参考LC718降维
        int[] dp = new int[n2 + 1]; //以j - 1为尾的最大长度
        int res = 0;
        for (int i = 1; i <= n1; i++) {
            // 这里pre相当于 dp[i - 1][j - 1], 相对dp[1][1]这是dp[0][0]->dp[0]
            int pre = dp[0];
            for (int j = 1; j <= n2; j++) {
                int cur = pre; // 此时是dp[i - 1][j - 1]
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = pre + 1;
                    res = Math.max(res, dp[j]);
                } else { // 不要求连续，继承以前的最大值就好
                    // dp[j]     相当于   dp[i - 1][j]
                    // dp[j - 1] 相当于   dp[i][j - 1]
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                // 相当于下一次的 dp[i - 1][j - 1]
                pre = cur;
            }
        }
        return res;
    }
}
