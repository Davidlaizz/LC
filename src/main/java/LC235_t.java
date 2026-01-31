/*
    结合LC236一起理解
 */
public class LC235_t {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        // 利用二叉搜索树的大小特性，以及pq必定存在的题目要求，
        // 得到：从上到下遍历的时候，得到节点值位于pq之间就是最近公共祖先

        //先根据二叉树特性找对边
        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        else{// root位于pq之间的情况
            return root;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        // 利用二叉搜索树的大小特性，以及pq必定存在的题目要求，
        // 得到：从上到下遍历的时候，得到节点值位于pq之间就是最近公共祖先
        while(root != null){
            //先根据二叉树特性找对边
            if(p.val < root.val && q.val < root.val){
                root = root.left;
            }
            else if(root.val < p.val && root.val < q.val){
                root = root.right;
            }
            else{// root位于pq之间的情况
                return root;
            }
        }
        //因为必有pq，所以不存在null,为了过编译
        return null;
    }
}
