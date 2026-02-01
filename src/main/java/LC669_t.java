public class LC669_t {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return null;
        if(root.val < low){
            return trimBST(root.right, low, high);
            //不是只返回root.right，因为root.right的最左节点可能>=low，所以继续trimBst()
        }
        if(high < root.val){
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    public TreeNode trimBST2(TreeNode root, int low, int high) {
        // 迭代法：找到合法root后，再分别修剪小于左边界、大于右边界的节点
        if(root == null) return null;
        // 找到合法root
        while(root != null && (root.val < low || high < root.val)) {
            if(root.val < low)
                root = root.right;
            else
                root = root.left;
        }

        // 修剪小于左边界的节点
        TreeNode cur = root;
        while(cur != null){
            while(cur.left != null && cur.left.val < low){ // 找小于的节点
                // 跳过这个不符合的cur.left，cur先连接cur.left.right，在下个循环检查新的cur.left
                cur.left = cur.left.right;
            }
            // 在下个循环检查新的cur.left
            cur = cur.left;
        }

        // 修剪大于右边界的节点
        cur = root;
        while(cur != null){
            while(cur.right != null && high < cur.right.val){ // 找小于的节点
                // 跳过这个不符合的cur.right，cur先连接cur.right.left，在下个循环检查新的cur.right
                cur.right = cur.right.left;
            }
            // 在下个循环检查新的cur.right
            cur = cur.right;
        }

        return root;
    }
}
