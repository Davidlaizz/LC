import java.util.List;

public class LC139_dp26 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 物品是字符串
        // 完全背包 物品从左往右遍历
        // 要求物品是排列数（顺序不定）, 先背包后物品
        int n = s.length();
        // 长度为i的字符串能否被wordDict构成（0, 1）
        int[] dp = new int[n + 1];
        dp[0] = 1; // 表示能凑成空字符串
        for (int i = 0; i <= n; i++) {
            for (String word : wordDict) {
                int size = word.length();
                // s.substring(i - size, i) 表示[i - size, i)对应dp[i]表示长度为i也就是最大下标是i-1
                if (i - size >= 0 && s.substring(i - size, i).equals(word)) {
                    dp[i] = Math.max(dp[i], dp[i - word.length()]);
                }
            }
        }
        return dp[n] == 1;
    }
}
