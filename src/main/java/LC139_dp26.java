import java.util.List;

public class LC139_dp26 {
    // 方法: 动态规划(完全背包-排列)
    //   目标：判断字符串s能否由字典中的单词拼接而成
    //   思路：物品是单词(可重复使用)，求能否凑成字符串s
    //   状态：dp[i]表示长度为i的前缀能否被拼出(0/1)
    //   转移：若s[i-size,i)在字典中且dp[i-size]=1，则dp[i]=1
    //   遍历顺序：先背包后物品(排列问题，顺序有关)
    //   复杂度：时间O(n*m*len)，空间O(n)
    //          n是s长度，m是字典大小，len是平均单词长度
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
