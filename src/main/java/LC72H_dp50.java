public class LC72H_dp50 {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for(int i = 0; i <= n1; i++){
            dp[i][0] = i;
        }
        for(int i = 0; i <= n2; i++){
            dp[0][i] = i;
        }
        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1), dp[i - 1][j - 1] + 1); // 和LC583唯一区别
                                                // 分别是 增 删 替换
                }

            }
        }
        // for(int i = 0 ; i <= n1; i++){
        //     for(int j = 0; j <= n2; j++){
        //         System.out.print(dp[i][j] + " ");
        //         if(j == n2) System.out.println(" ");
        //     }
        // }
        return dp[n1][n2];
    }

    public int minDistance2(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        // 以i-1为结尾的字符串word1，和以j-1位结尾的字符串word2，想要达到相等，所需要删除元素的最少次数。
        int[] dp = new int[n2 + 1];
        // 一边空串就要把另一边全删掉
        for(int i = 0; i <= n2; i++){
            dp[i] = i;
        }
        for(int i = 1; i <= n1; i++){
            // 1. 把上一轮的 dp[i - 1][j - 1] (也就是 dp[i-1][0] = 0) 交给 pre
            int pre = dp[0];
            // 2. 【关键修复】立刻更新当前的 dp[0] 为 i！
            // 相当于初始化二维数组的 dp[i][0] = i (word2为空，全删word1)
            dp[0] = i;
            for(int j = 1; j <= n2; j++){
                int cur = dp[j];
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[j] = pre;
                }else{ // 不相等就删一个，退化到前面的状态（前面的状态可能符合也可能再退化 不用管了）
                    dp[j] = Math.min(Math.min(dp[j - 1] + 1, dp[j] + 1), pre + 1);
                }
                pre = cur;
            }
        }
        return dp[n2];
    }
}
