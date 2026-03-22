public class LC647H_dp52 {
    public int countSubstrings(String s) {
        int n = s.length();
        //表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串
        boolean dp[][] = new boolean[n][n];
        int res = 0;
        // 遍历顺序：dp[i][j]由dp[i + 1][j - 1]决定
        // 所以一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的。
        for(int i = n; i >= 0; i--){
            for(int j = i; j < n; j++){ // j代表回文子串的右边界, dp[][]区间范围[i,j]
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i < 2){ // 两个或一个元素时
                        dp[i][j] = true;
                        res++;
                    }else{
                        if(dp[i + 1][j - 1]){
                            dp[i][j] = true;
                            res++;
                        }
                    }
                }

            }
        }
        return res;

    }
}
