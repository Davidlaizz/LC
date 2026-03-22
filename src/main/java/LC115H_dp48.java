class LC115H_dp48 {
    public int numDistinct(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        if(n1 < n2) return 0;
        int dp[][] = new int[n1 + 1][n2 + 1]; //i-1,j-1为尾的s，t的子序列个数
        dp[0][0] = 1; // [0]表示空串
        for(int i = 1 ; i <= n1; i++){
            dp[i][0] = 1; //s可以得到t
        }
        for(int i = 1 ; i <= n2; i++){
            dp[0][i] = 0; //s为空，永不得到t
        }
        for(int i = 1; i <= n1; i++){ //t
            for(int j = 1; j <= n2; j++){ //s
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1]; //不选s.charAt(i - 1)和 选 ，两种都要考虑
                }else{
                    // System.out.println(i +" " +  j);
                    dp[i][j] = dp[i - 1][j]; // 选不了当前s.charAt(i - 1)，继承前面的个数
                }
            }
        }
        return dp[n1][n2];
    }

    public int numDistinct2(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        if(n1 < n2) return 0;
        int dp[] = new int[n2 + 1]; //i-1,j-1为尾的s，t的子序列个数
        dp[0] = 1; // [0]表示空串
        for(int i = 1; i <= n1; i++){ //t
            int pre = dp[0]; // 关键
            for(int j = 1; j <= n2; j++){ //s
                int cur = dp[j]; // 关键: 保留的是还没被修改的dp[j]也是下一轮的dp[j - 1]
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[j] = dp[j] + pre; //不选s.charAt(i - 1)和 选 ，两种都要考虑
                }else{
                    // System.out.println(i +" " +  j);
//                    dp[j] = dp[j]; // 选不了当前s.charAt(i - 1)，继承前面的个数
                }
                pre = cur; // 关键
            }
        }
        return dp[n2];
    }
}