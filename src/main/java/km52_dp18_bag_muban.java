import java.util.*;
public class km52_dp18_bag_muban {
    // 完全背包和01背包问题唯一不同的地方就是，每种物品有无限件。
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int v = sc.nextInt();
        int weight[] = new int[n];
        int val[] = new int[n];
        for(int i = 0; i < n; i++){
            weight[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }

        int dp[] = new int[v + 1];
        for(int i = 0; i < n; i++){
            for(int j = weight[i]; j <= v; j++){ // 重点
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + val[i]);
            }
            // for(int x : dp){
            //     System.out.print(x + " ");
            // }
            // System.out.println(" ");
        }

        System.out.println(dp[v]);
    }
}