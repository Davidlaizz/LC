import java.util.Arrays;

public class LC1049_dp14 {
    // 类LC416 背包容量j和价值都是相同的
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        int[] dp = new int[target + 1]; // 容量为j的背包能带最大价值dp
        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[target];
    }


}
