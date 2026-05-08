public class LC226_t {
    // 方法: 递归翻转二叉树
    //   思路：先递归翻转左右子树，再交换当前节点的left/right
    //   本质：对每个节点执行一次“左右子节点交换”
    //   终止：空节点直接返回
    //   复杂度：时间O(n)，空间O(h)
    //          h为树高，递归栈最坏O(n)
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
