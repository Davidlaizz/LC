import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LC94_t {
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

    //重点：栈代替递归
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root == null) return List.of();
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
