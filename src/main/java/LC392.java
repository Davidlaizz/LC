public class LC392 {
    //贪心
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
        int dp[][] = new int[n1 + 1][n2 + 1];
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
}
