import java.util.*;

public class LC144H_t {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }
    public void preorder(TreeNode root, List<Integer> res){
        if(root == null)
            return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    //非NULL的前序遍历模板
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> s = new ArrayDeque<>();
        while(root != null || !s.isEmpty()){
            //根左
            while(root != null){
                res.add(root.val); //先根再左
                s.push(root);
                root = root.left;
            }
            //根
            TreeNode node = s.pop();
            //右
            root = node.right;
        }
        return res;
    }

    //用 null 标记和栈来实现迭代遍历
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            //前序：根左右
            //入栈：右左根
            if(node != null){
                if(node.right != null) //入栈右
                    s.push(node.right);
                if(node.left != null) //入栈左
                    s.push(node.left);
                s.push(node); //入栈根
                s.push(null);
            }
            //出栈：根左右
            else{ //node == null表示 下一个栈顶元素改出栈了
                TreeNode top = s.pop();
                res.add(top.val);
            }
        }
        return res;
    }
}
