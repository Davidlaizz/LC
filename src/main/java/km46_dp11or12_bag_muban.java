import java.util.*;
public class km46_dp11or12_bag_muban {
    /* 小明的行李空间为 N，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料只能选择一次，并且只有选与不选两种选择，不能进行切割。

    输入描述
    第一行包含两个正整数，第一个整数 M 代表研究材料的种类，第二个正整数 N，代表小明的行李空间。
    第二行包含 M 个正整数，代表每种研究材料的所占空间。
    第三行包含 M 个正整数，代表每种研究材料的价值。

    输出描述
    输出一个整数，代表小明能够携带的研究材料的最大价值。
    输入示例
        6 1
        2 2 3 1 5 2
        2 3 1 5 4 3
    输出示例
        5
    提示信息
    小明能够携带 6 种研究材料，但是行李空间只有 1，而占用空间为 1 的研究材料价值为 5，所以最终答案输出 5。
    数据范围：
            1 <= N <= 5000
            1 <= M <= 5000
    研究材料占用空间和价值都小于等于 1000
    */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        int space[] = new int[M];
        for (int i = 0; i < M; i++) {
            space[i] = sc.nextInt();
        }
        int val[] = new int[M];
        for (int i = 0; i < M; i++) {
            val[i] = sc.nextInt();
        }
        int dp[] = new int[N + 1];
        //初始化为0，容量0价值也是0

        for (int i = 0; i < M; i++) {
            for (int j = N; j > 0; j--) { // 状态压缩，因为只选一次，所以从右往左
                if (j - space[i] < 0) continue;
                // dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - space[i]] + val[i]);
                dp[j] = Math.max(dp[j], dp[j - space[i]] + val[i]);
            }
        }
        System.out.println(dp[N]);

        // int dp[][] = new int[M][N + 1];
        // for(int i = 0; i <= N; i++){
        //     if(i < space[0]) continue;
        //     dp[0][i] = val[0];
        // }

        // for(int i = 1; i < M; i++){
        //     for(int j = 0; j <= N; j++){
        //         if(j - space[i] < 0){
        //             dp[i][j] = dp[i - 1][j];
        //         }else{
        //             dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - space[i]] + val[i]);
        //         }

        //     }
        // }
        // System.out.println(dp[M - 1][N]);
    }

}
