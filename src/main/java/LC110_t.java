public class LC110_t {
    boolean bal = true;
    public boolean isBalanced(TreeNode root) {
        getDep(root);
        return bal;
    }
    public int getDep(TreeNode root){
        if(root == null) return 0;
        //左右根
        int ldep = getDep(root.left);
        int rdep = getDep(root.right);
        if(Math.abs(ldep - rdep) > 1){
            bal = false;
        }
        return Math.max(ldep, rdep) + 1;
    }

    //方法2：用栈迭代后序遍历，再用层次遍历计算深度/高度
    //完全没必要，这两次遍历浪费性能
}
