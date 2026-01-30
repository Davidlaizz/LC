import java.util.Stack;

public class LC530_t {
    public int getMinimumDifference(TreeNode root) {
        // 迭代 中序遍历 出栈左根右小到大 , 递归也类似（省略）
        // if(root == null) return 0;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode pre = null;
        int min = Integer.MAX_VALUE;
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            if(node != null){
                if(node.right != null)
                    s.push(node.right);
                s.push(node);
                s.push(null);
                if(node.left != null)
                    s.push(node.left);
            }else{
                TreeNode cur = s.pop();
                if(pre != null)
                    min = Math.min(cur.val - pre.val, min);
                // 记录本次节点作为历史节点
                pre = cur;
            }
        }
        return min;
    }
}
