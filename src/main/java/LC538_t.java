import java.util.Stack;

public class LC538_t {
    // TreeNode pre; 要不记住前驱要不直接用sum
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        // 右根左
        convertBST(root.right);
        // if(pre != null)
        //     root.val += pre.val;
        // pre = root;
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    public TreeNode convertBST2(TreeNode root) {
        //迭代：记录前驱。 右根左
        if(root == null) return null;
        Stack<TreeNode> s = new Stack<>();
        // TreeNode pre = null;
        int sum = 0;
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            if(node != null){
                if(node.left != null)
                    s.push(node.left);
                s.push(node);
                s.push(null);
                if(node.right != null)
                    s.push(node.right);
            }else{
                TreeNode cur = s.pop();
                cur.val += sum;
                sum = cur.val;
                // if(pre != null)
                //     cur.val += pre.val;
                // pre = cur;
            }
        }
        return root;
    }
}
