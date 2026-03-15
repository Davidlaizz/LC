public class LC96H_dp9 {
    public int numTrees(int n) {
        int dp[] = new int[n+1];
        dp[0] = 1; //i个节点的树的数量
        // 想象成1 ~ n的链表,分别考虑各个i为父节点
        for(int i = 1; i <= n; i++){
            // 考虑子节点情况，既i-1个节点的排列数量
            // dp[以j为头结点左子树节点数量] * dp[以j为头结点右子树节点数量]
            for(int j = 0; j < i; j++){
                // 提出值为i的节点,剩下i - 1个节点组合
                dp[i] += dp[j] * dp[i - j - 1];
                // System.out.println("dp[" + i + "] = " + dp[i]);
            }
        }
        return dp[n];
    }
}
