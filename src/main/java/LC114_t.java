class LC114_t {
    // 方法: 先序遍历收集 + 顺序重连
    //   思路：先按先序(根左右)把节点存入列表，列表顺序即展开后的目标顺序
    //   重连：遍历列表，把前一个节点的left置空、right指向当前节点
    //   结果：原树被原地改造成“仅右指针”的链表
    //   复杂度：时间O(n)，空间O(n)
    //          额外空间来自先序列表和递归栈
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }
}
