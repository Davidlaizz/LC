import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC543_t {
    // 方法1: 递归后序(推荐)
    //   思路：对每个节点计算左右子树深度，直径候选为leftDepth + rightDepth
    //   更新：在求深度的过程中同步维护全局最大直径max
    //   本质：直径问题转化为“每个节点左右深度和”的最大值
    //   复杂度：时间O(n)，空间O(h)
    //
    // 方法2: 迭代后序 + 哈希表记深度
    //   思路：用栈模拟后序遍历(左右根)，Map记录每个节点的子树深度
    //   更新：节点出栈处理时，基于左右深度更新当前直径并维护全局最大值
    //   特点：避免递归，逻辑更通用但实现更繁琐
    //   复杂度：时间O(n)，空间O(n)
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDep(root);
        return max;
    }
    //本质还是求最大深度LC104，过程中顺便记录每个左右子节点的深度和
    public int maxDep(TreeNode root){
        if(root == null)
            return 0;
        //子节点
        int dep1 = maxDep(root.left);
        int dep2 = maxDep(root.right);
        //更新直径
        max = Math.max(dep1 + dep2, max);
        //root的深度
        return Math.max(dep1, dep2) + 1;
    }

    //迭代 京东二面
    //递归：在栈保存所有顶层节点 再出栈：左右根 后序
    public int diameterOfBinaryTree2(TreeNode root) {
        if(root == null)
            return 0;
        int len = 0;
        Map<TreeNode, Integer> treeDeepMap = new HashMap<>();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            //入栈：根右左
            if(node != null){
                s.push(node);
                s.push(null);
                if(node.right != null)
                    s.push(node.right);
                if(node.left != null)
                    s.push(node.left);
            }else{//出栈左右根，自底向上
                TreeNode top = s.pop();
                //记录节点对应高度
                int deep = 1 + Math.max(treeDeepMap.getOrDefault(top.left, 0),
                        treeDeepMap.getOrDefault(top.right, 0));
                treeDeepMap.put(top, deep);
                //以当前节点为根，左右子树的最大深度,不算节点本身
                int curLen = treeDeepMap.getOrDefault(top.left, 0) + treeDeepMap.getOrDefault(top.right, 0);
                len = Math.max(len, curLen);
            }
        }
        return len;
    }
}
