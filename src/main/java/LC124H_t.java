public class LC124H_t {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        postOrder(root);
        return max;
    }
    public int postOrder(TreeNode root) {
        if(root == null)
            return 0;
        int l = postOrder(root.left);
        int r = postOrder(root.right);
        // 修剪左右子树和，大于0才值得记录
        if(l > 0){
            // max = Math.max(max, l); l和r在int l = postOrder(root.left);他们自己的函数里以及统计过最大值了
        }else{
            l = 0; // l < 0 则弃用这一个子树
        }
        if(r > 0){
            // max = Math.max(max, r);
        }else{
            r = 0;
        }
        // 以当前root为根，结合左右子树可能是最大值
        max = Math.max(max, r + l + root.val);
        // 只记录最大的一边
        root.val = Math.max(l + root.val, r + root.val);
        return root.val;
    }
}
