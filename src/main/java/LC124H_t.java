public class LC124H_t {
    // 方法: 后序遍历 + 贪心剪枝
    //   目标：找任意路径（不一定经过根）的最大和
    //   思路：对每个节点，计算"左子树贡献"和"右子树贡献"
    //   剪枝：若子树贡献 < 0，则弃用（置为0），因为加上只会让路径和变小
    //   更新：以当前节点为拐点的路径和 = l + r + root.val，更新全局max
    //   返回：向上只能传一边的路径，即 max(l, r) + root.val
    //   复杂度：时间O(n)，空间O(h)
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
