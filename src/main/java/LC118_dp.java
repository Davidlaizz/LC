import java.util.ArrayList;
import java.util.List;

public class LC118_dp {
    // 方法: 动态规划
    //   目标：生成杨辉三角的前numRows行
    //   思路：每行首尾为1，中间元素等于上一行相邻两元素之和
    //   状态：dp[i][j]表示第i行第j列的值
    //   转移：dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
    //   边界：j==0 或 j==i 时为1
    //   复杂度：时间O(n²)，空间O(n²)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dp = new ArrayList<List<Integer>>();
        for(int i = 0; i < numRows; i++){ // 遍历行
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j <= i; j++){ // 遍历行中元素
                if(j == 0 || j == i){
                    row.add(1);
                }else{
                    row.add(dp.get(i - 1).get(j - 1) + dp.get(i - 1).get(j));
                }
            }
            dp.add(row);
        }
        return dp;
    }
}
