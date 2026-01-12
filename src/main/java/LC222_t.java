public class LC222_t {
    public int countNodes(TreeNode root) {
        //遍历 On的复杂度
        if(root == null) return 0;
        //左右根
        int leftNum = countNodes(root.left);
        int rightNum = countNodes(root.right);
        return leftNum + rightNum + 1;
    }

    //利用完全树的特性，优化复杂度
    public int countNodes2(TreeNode root) {
        if(root == null) return 0;
        int ldep = 1, rdep = 1; //自身算一层
        TreeNode left = root.left;
        TreeNode right = root.right;
        //最左边深度
        while(left != null){
            ldep++;
            left = left.left;
        }
        //最右边深度
        while(right != null){
            rdep++;
            right = right.right;
        }
        //以root为根的树是完全树，满二叉树的结点数为：2^depth - 1
        if(ldep == rdep) return (int)Math.pow(2, ldep) - 1;
        //不是完全二叉树，左右深入
        int lnum = 0, rnum = 0;
        lnum = countNodes(root.left);
        rnum = countNodes(root.right);
        return lnum + rnum + 1;
    }
}
