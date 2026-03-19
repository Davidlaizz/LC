import java.util.*;

public class km56_dp27_bag_muban{
    public static void main(String[] args) {
        // 多重背包 == 有限制的完全背包 == 多维01背包（组合数的情况）
        // 处理输入
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        int N = sc.nextInt();
        int[] weight = new int[N];
        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
        }
        int[] val = new int[N];
        for (int i = 0; i < N; i++) {
            val[i] = sc.nextInt();
        }
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        int[] dp = new int[C + 1];
        // 组合数：只要找到最大值即可，不关心谁先谁后
        // 先物品后背包
        for (int i = 0; i < N; i++) {
            for (int j = C; j >= weight[i]; j--) {
                // 在01背包的基础上，添加限制个数的遍历，注意k的条件
                for (int k = 1; k <= nums[i] && (j - k * weight[i] >= 0); k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * val[i]);
                }
            }
        }
        System.out.println(dp[C]);
    }
}