import java.util.ArrayDeque;
import java.util.Deque;

public class LC104_t {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        //层序遍历模板
        Deque<TreeNode> q = new ArrayDeque<>();
        int dep = 0;
        if(root == null) return dep;
        q.offer(root);
        while(!q.isEmpty()){
            dep++;
            int size = q.size();
            while(size-- > 0){
                TreeNode cur = q.poll();
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }
        }
        return dep;
    }
}
