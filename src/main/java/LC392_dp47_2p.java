public class LC392_dp47_2p {
    //双指针
    public boolean isSubsequence1(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        if(n1 > n2) return false;
        int i = 0, j = 0;
        while(i < n1 && j < n2){
            if(s.charAt(i) == t.charAt(j)){
                i++;
                j++;
            }else{
                if(++j == n2){
                    return false;
                }
            }
        }
        return i == n1;
    }

    //动态规划
    public boolean isSubsequence(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        // dp[i][j] 表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]。
        int[][] dp = new int[n1 + 1][n2 + 1];
        for(int i = 1; i <= n1; i++){
            for (int j = 1; j <= n2; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2] == n1;
    }

    // 状态压缩 需要调整遍历顺序
    public boolean isSubsequence3(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        int[] dp = new int[n1 + 1]; // 压缩掉长的t
        for (int j = 1; j <= n2; j++){
            for(int i = n1; i >= 1; i--){ // 连续的子串应该放在第二次遍历，否则导致状态不连贯
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i] = dp[i - 1] + 1;
                }else{
                    dp[i] = Math.max(dp[i - 1], dp[i]);
                }
            }
        }
        return dp[n1] == n1;
    }
}
