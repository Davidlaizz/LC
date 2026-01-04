public class LC226_t {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return root;
        //先反转底层二叉树
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        //反转本身
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }
}
