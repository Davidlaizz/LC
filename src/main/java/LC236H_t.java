import java.util.Stack;

public class LC236H_t {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == q || root == p) return root; //找到就直接返回
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if(l == null && r == null) return null; //未找到
        if(l != null && r != null) return root; //刚找到root就是最近祖先
        if(l != null && r == null) return l;
        if(l == null && r != null) return r;
        return null;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        int max = (int)Math.pow(10, 6);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node != null) {
                // ===== 入栈阶段 =====
                stack.push(node);
                stack.push(null); // 标记该节点“已访问”

                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);

            } else {
                // ===== 出栈阶段（真正的后序处理）=====
                TreeNode cur = stack.pop();

                // 情况 1：cur 是 p 或 q
                if (cur == p || cur == q) {
                    // 子树中已经出现过另一个
                    if ((cur.left != null && cur.left.val == max) ||
                            (cur.right != null && cur.right.val == max)) {
                        return cur;
                    }
                    else// 否则向上传递标记
                        cur.val = max;
                }

                // 情况 2：左右子树都出现过
                if (cur.left != null && cur.left.val == max &&
                        cur.right != null && cur.right.val == max) {
                    return cur;
                }

                // 情况 3：只有一边出现过，继续向上传
                if ((cur.left != null && cur.left.val == max) ||
                        (cur.right != null && cur.right.val == max)) {
                    cur.val = max;
                }
            }
        }
        return null;
    }
}
