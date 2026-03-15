import java.util.*;

public class LC94_t_muban {
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
