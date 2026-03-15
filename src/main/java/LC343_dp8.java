public class LC343_dp8 {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        // dp[i]表示拆分i,得到的最大乘积
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // 拆两次,拆多次,以及得到多次拆分后的最大值(重点)
                dp[i] = Math.max(Math.max(j * (i - j), j * dp[i - j]), dp[i]);
            }
        }
        return dp[n];
    }
}
