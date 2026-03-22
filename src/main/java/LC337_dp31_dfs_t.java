import java.util.HashMap;
import java.util.Map;

public class LC337_dp31_dfs_t {
    // 三种方法发起的方向是自顶向下（递归），但实际计算和赋值的顺序是自底向上（后序遍历）。
    // 记忆化dfs和树形dp 时间复杂度On 空间lgn
    // dfs的优势是自顶向下，可以根绝结果只计算部分信息；dp则会计算每个小结果向上找答案；
    // 但是在这个例子中都需要遍历一遍，所以记忆化的HashMap成为劣势（拆箱 + 扩容）

    // 递归
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        // 不偷root
        int res1 = rob(root.left) + rob(root.right);
        // 偷root和root.son.son
        int res2 = root.val;
        if (root.left != null)
            res2 += rob(root.left.left) + rob(root.left.right);
        if (root.right != null)
            res2 += rob(root.right.left) + rob(root.right.right);
        return Math.max(res1, res2);
    }

    // 记忆化dfs
    Map<TreeNode, Integer> memo = new HashMap<>();
    public int rob2(TreeNode root) {
        if (root == null)
            return 0;
        // 记忆化剪枝
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 不偷root
        int res1 = rob(root.left) + rob(root.right);
        // 偷root和root.son.son
        int res2 = root.val;
        if (root.left != null)
            res2 += rob(root.left.left) + rob(root.left.right);
        if (root.right != null)
            res2 += rob(root.right.left) + rob(root.right.right);
        int curMax = Math.max(res1, res2);
        memo.put(root, curMax);
        return curMax;
    }

    // 树形dp：自底向上
    public int rob3(TreeNode root) {
        // 状态压缩成偷或不偷两种情况，没必要像memo一样
        int[] dp = robTree(root);
        return Math.max(dp[0], dp[1]);
    }
    public int[] robTree(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        // 后序遍历，在返回向上的时候计算（dfs向下的时候计算）
        int[] leftdp = robTree(root.left);
        int[] rightdp = robTree(root.right);
        // 偷root + 不偷root.son
        int res1 = root.val + leftdp[0] + rightdp[0];
        // 不偷root + 偷root.son
        int res2 = leftdp[1] + rightdp[1];
        return new int[]{res2, res1};
    }
}
