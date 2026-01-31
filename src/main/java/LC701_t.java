public class LC701_t {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 不平衡的情况下，只考虑在叶子节点插入即可，不破坏原结构
        if(root == null){
            return new TreeNode(val);
        }
        if(val < root.val){
            root.left = insertIntoBST(root.left, val);
        }
        if(root.val < val){
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        // 迭代法：1.找对应叶子节点 2.插入
        if(root == null) return new TreeNode(val);
        TreeNode newroot = root;
        TreeNode pre = root;
        // 1.
        while(root != null){
            // 记录前一个节点，才能找到叶子节点
            pre = root;
            if(val < root.val){
                root = root.left;
            }
            else if(root.val < val){
                root = root.right;
            }
        }
        // 2.
        if(val < pre.val)
            pre.left = new TreeNode(val);
        if(val > pre.val)
            pre.right = new TreeNode(val);
        return newroot;
    }
}
