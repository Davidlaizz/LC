import java.util.Stack;

public class LC112_t {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return backTracking(root, targetSum, 0);
    }
    public boolean backTracking(TreeNode root, int targetSum, int curSum){
        if(root.left == null && root.right == null){
            if(targetSum == curSum + root.val)
                return true;
            else
                return false;
        }
        boolean l = false, r = false;
        if(root.left != null){
            l = backTracking(root.left, targetSum, curSum + root.val);
            if (l) return true; // 显式剪枝：左边找到了，直接回传 true，不再看右边
        }
        if(root.right != null){
            r = backTracking(root.right, targetSum, curSum + root.val);
            if (r) return true;
        }
        return false;
    }


    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root == null) return false;
        Stack<TreeNode> s = new Stack<>();
        Stack<Integer> si = new Stack<>();
        s.push(root);
        si.push(root.val);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            Integer value = si.pop(); // 注意：这里必须用 Integer 接收，不能用 int，否则 pop 出 null 时会拆箱报错
            if(node != null){
                s.push(node);
                s.push(null);
                si.push(value);
                si.push(null);
                if(node.left != null){
                    s.push(node.left);
                    si.push(value + node.left.val);
                }
                if(node.right != null){
                    s.push(node.right);
                    si.push(value + node.right.val);
                }
            }else{
                TreeNode cur = s.pop();
                Integer sum = si.pop();
                if(cur.left == null && cur.right == null && sum == targetSum)
                    return true;
            }
        }
        return false;
    }
}
