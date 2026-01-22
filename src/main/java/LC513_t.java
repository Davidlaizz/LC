import java.util.ArrayDeque;
import java.util.Queue;

public class LC513_t {
    int dep;
    int res;
    public int findBottomLeftValue(TreeNode root) {
        dep = 1;
        res = root.val;
        backTracking(root, 1);
        return res;
    }
    public void backTracking(TreeNode root, int curDep){
        if(root == null) return;
        if(root.left == null && root.right == null && curDep > dep){ //当前是叶子节点（可以是父节点的右叶子），且更深则记录 // 如果相同深度的右边节点是curDep==dep
            dep = curDep;
            res = root.val;
        }
        curDep++;
        backTracking(root.left, curDep);
        backTracking(root.right, curDep);
    }

    public int findBottomLeftValue2(TreeNode root) {
        //假设二叉树中至少有一个节点。
        int res = root.val;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            res = q.peek().val;
            while(size-- > 0){
                TreeNode cur = q.poll();
                if(cur.left != null)
                    q.offer(cur.left);
                if(cur.right != null)
                    q.offer(cur.right);
            }
        }
        return res;
    }
}
