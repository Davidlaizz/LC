public class LC474_dp17 {
    public int findMaxForm(String[] strs, int m, int n) {
        // 数组有三个维度
        // 第一个维度：取前面的几个字符串
        // 第二个维度：0的数量限制（背包维度 1 容量）
        // 第三个维度：1的数量限制（背包维度 2 容量）
        // int[][][] dpArr = new int[strs.length][m + 1][n + 1];
        // 二维的01背包，压缩掉物品的维度
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
        for (String str : strs) {
            int zeroNum = 0;
            int oneNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            // 状态压缩01背包,从右向左
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
