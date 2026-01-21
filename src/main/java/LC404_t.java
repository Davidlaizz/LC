import java.util.Stack;

public class LC404_t {

    int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        travel(root);
        return res;
    }
    public void travel(TreeNode root){
        if(root == null) return;

        //判断条件 根 可以放在任何位置 前中后序遍历
        if(root.left != null && root.left.left == null && root.left.right == null){
            res += root.left.val;
        }

        travel(root.left);
        travel(root.right);
    }

    //迭代  前中后序不重要 重要的是判断条件
    public int sumOfLeftLeaves2(TreeNode root) {
        int res = 0;
        if(root == null) return 0;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            if(node != null){
                s.push(node);
                s.push(null);
                if(node.left != null)
                    s.push(node.left);
                if(node.right != null)
                    s.push(node.right);
            }else{
                TreeNode cur = s.pop();
                if(cur.left != null && cur.left.left == null && cur.left.right == null)
                    res += cur.left.val;
            }
        }
        return res;
    }

}
