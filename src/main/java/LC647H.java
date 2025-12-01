import java.util.Stack;

public class LC647H {
    public int countSubstrings(String s) {
        int n = s.length();
        //表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串
        boolean dp[][] = new boolean[n][n];
        int res = 0;
        for(int i = n; i >= 0; i--){
            for(int j = i; j < n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i < 2){
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
