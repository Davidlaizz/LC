public class LC450_t {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 五种情况：1.找不到不删除 2-5.找到了node：考虑node的左/右子树 + 空/不空
        // 1.找不到
        if(root == null) return null;
        // 2-5.找到了
        if(root.val == key){
            // 2.左右都空，叶子节点
            if(root.left == null && root.right == null){
                return null;
            }
            // 3.左不空右空
            if(root.left != null && root.right == null){
                return root.left; //忽略掉root
            }
            // 4.左空右不空
            if(root.left == null && root.right != null){
                return root.right;
            }
            // 5.左右都不空：将左子树 挪到 右子树的最左节点的左边
            // 找右子树的最左节点
            TreeNode cur = root.right;
            while(cur.left != null){
                cur = cur.left;
            }
            // 挪树
            cur.left = root.left;
            return root.right;
        }
        // 没找到继续往下找找，利用二叉搜索树加速
        if(key < root.val)
            root.left = deleteNode(root.left, key);
        if(root.val < key)
            root.right = deleteNode(root.right, key);
        return root;
    }
}
