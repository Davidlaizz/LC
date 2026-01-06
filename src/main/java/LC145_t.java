import java.util.*;

public class LC145_t {
    //递归省略

    //非NULL的后序遍历模板
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> s = new ArrayDeque<>();
        //根右左 反转成 左右根
        while (root != null || !s.isEmpty()) {
            //根右
            while (root != null) {
                res.add(root.val);
                s.push(root);
                root = root.right;
            }
            //根
            TreeNode node = s.pop();
            //左
            root = node.left;
        }
        Collections.reverse(res);
        return res;
    }

    //用 null 标记和栈来实现迭代遍历
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            //后序：左右根
            //入栈：根右左
            if(node != null){
                s.push(node); //入栈根
                s.push(null);
                if(node.right != null) //入栈右
                    s.push(node.right);
                if(node.left != null) //入栈左
                    s.push(node.left);
            }
            //出栈：左右根
            else{ //node == null表示 下一个栈顶元素改出栈了
                TreeNode top = s.pop();
                res.add(top.val);
            }
        }
        return res;
    }
}
