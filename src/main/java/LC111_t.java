import java.util.ArrayDeque;
import java.util.Deque;

public class LC111_t {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int minLeft = minDepth(root.left);
        int minRight = minDepth(root.right);
        //单子节点的情况，单子节点不是叶子节点
        if(root.left == null) return minRight + 1;
        if(root.right == null) return minLeft + 1;
        //双子节点
        return Math.min(minLeft, minRight) + 1;
    }

    public int minDepth2(TreeNode root) {
        //层序遍历 迭代 判断每一层是否有叶子节点
        if(root == null) return 0;
        Deque<TreeNode> q = new ArrayDeque<>();
        int dep = 0;
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            dep++;
            while(size-- > 0){
                TreeNode cur = q.poll();
                if(cur.left == null && cur.right == null) return dep;
                if(cur.left != null)
                    q.offer(cur.left);
                if(cur.right != null)
                    q.offer(cur.right);
            }
        }
        return dep;
    }
}
