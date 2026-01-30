import java.util.Stack;

public class LC700_t {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null)
            return null;
        if(root.val == val)
            return root;
        else if(val < root.val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        // 迭代，利用二叉搜索树特点，优化，可以不需要栈
        while(root != null){
            if(root.val == val)
                return root;
            else if(root.val > val)
                root = root.left;
            else
                root = root.right;
        }
        return null;
    }

    public TreeNode searchBST3(TreeNode root, int val) {
        // 迭代，栈, 模拟前序, 没有利用上搜索树的左小右大
        if(root == null) return null;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            if(node != null){
                if(node.right != null)
                    s.push(node.right);
                if(node.left != null)
                    s.push(node.left);
                s.push(node);
                s.push(null);
            }else{
                TreeNode cur = s.pop();
                if(cur.val == val)
                    return cur;
            }
        }
        return null;
    }
}
