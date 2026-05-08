import java.util.*;

public class LC94_t_muban {
    // 方法1: 递归中序
    //   顺序：左 -> 根 -> 右
    //   思路：递归遍历左子树，访问根，再递归遍历右子树
    //   复杂度：时间O(n)，空间O(h)
    //          h为树高，递归栈最坏O(n)
    //
    // 方法2: 传统迭代栈
    //   顺序：左 -> 根 -> 右
    //   思路：用指针cur不断向左入栈，回退时访问节点再转向右子树
    //   复杂度：时间O(n)，空间O(h)
    //
    // 方法3: 统一迭代模板(null标记法)
    //   思路：栈中用null作为“访问标记”，把处理节点与遍历节点拆开
    //   特点：前中后序可统一为一套模板，改压栈顺序即可
    //   复杂度：时间O(n)，空间O(h)
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }
    public void inorder(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    //重点：栈代替递归 通用模板
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> s = new LinkedList<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            if (node != null) {
                if (node.right != null)
                    s.push(node.right);
                s.push(node);
                s.push(null);
                if (node.left != null)
                    s.push(node.left);
            } else {
                TreeNode cur = s.pop();
                res.add(cur.val);
            }
        }
        return res;
    }

    // 传统的栈替代方法
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
//            return List.of();
        List<Integer> res = new ArrayList<>();
        //迭代，用Stack代替递归
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){ //左根右
            if(cur != null){//不断遍历左
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                res.add(cur.val); // 根
                cur = cur.right; // 右
            }
        }
        return res;
    }
}
