import java.util.HashMap;
import java.util.Map;

public class LC437_t_pre {
    // 方法1: 双重递归
    //   思路：对每个节点，计算以它为起点的路径数(rootSum)
    //         再递归处理左右子树，累加所有起点路径数
    //   本质：外层递归枚举起点，内层递归向下找路径
    //   复杂度：时间O(n²)，空间O(h)
    //          最坏每个节点都要向下遍历整棵子树
    //
    // 方法2: 前缀和 + HashMap(推荐)
    //   思路：记录根到当前节点的路径前缀和，若curSum - target在map中
    //         说明中间存在一段路径和为target
    //   回溯：进入子树前把当前前缀和入map，返回时减1(保证不同分支隔离)
    //   初始化：prefix.put(0L, 1)表示从根到某节点恰好等于target的情况
    //   复杂度：时间O(n)，空间O(n)
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
