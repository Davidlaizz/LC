import java.util.HashMap;
import java.util.Map;

public class LC437_t_pre {
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        int res = rootSum(root, targetSum);
        //前序遍历，再递归计算左右子树的路径个数
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }
    // 以当前root为根的路径个数
    public int rootSum(TreeNode root, int targetSum){
        if(root == null) return 0;
        int res = 0;
        if(targetSum - root.val == 0)
            res++;
        res += rootSum(root.left, targetSum - root.val);
        res += rootSum(root.right, targetSum - root.val);
        return res;
    }

    // MAP优化时间复杂度 O(N)
    Map<Long, Integer> prefix = new HashMap<>();
    public int pathSum2(TreeNode root, int targetSum) {
        if(root == null) return 0;
        prefix.put(0l, 1); // 前缀和cur : 当前分支出现次数
        return backTracking(root, targetSum, 0);
    }
    public int backTracking(TreeNode root, int targetSum, long curSum){
        if(root == null) return 0;
        int res = 0;
        curSum += root.val;
        // 检查是否存在当前节点到前面某个节点的符合条件的情况
        if(prefix.containsKey(curSum - targetSum))
            res += prefix.get(curSum - targetSum);
        // 添加当前节点的路径
        prefix.put(curSum, prefix.getOrDefault(curSum, 0) + 1);
        res += backTracking(root.left, targetSum, curSum);
        res += backTracking(root.right, targetSum, curSum);
        // 别的分支里用不到当前路径的前缀和，回溯
        prefix.put(curSum, prefix.get(curSum) - 1);
        return res;
    }
}
