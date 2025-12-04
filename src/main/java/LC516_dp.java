public class LC516_dp {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        //表示区间范围[i,j] （注意是左闭右闭）的串
        int dp[][] = new int[n][n];
        for(int i = n; i >= 0; i--){
            for(int j = i; j < n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(i == j){
                        dp[i][j] = 1;
                    }
//                    else if(i + 1 == j){
//                        dp[i][j] = 2;
//                    }
                    else{
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //看成s和s的反串的最大公共子序列
    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        //表示区间范围[i,j] （注意是左闭右闭）的串
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String t = sb.toString();
        int dp[][] = new int[n + 1][n + 1];//i - 1, j - 1为尾的最最大公共子序列大小
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }
}
