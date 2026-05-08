import java.util.ArrayDeque;
import java.util.Deque;

public class LC104_t {
    // 方法1: 递归(后序思想)
    //   思路：节点深度 = max(左子树深度, 右子树深度) + 1
    //   终止：空节点深度为0
    //   复杂度：时间O(n)，空间O(h)
    //          h为树高，递归栈最坏O(n)
    //
    // 方法2: 层序遍历(BFS)
    //   思路：按层遍历，每处理完一层，深度dep+1
    //   关键：每轮先记录当前层节点数size，确保分层准确
    //   复杂度：时间O(n)，空间O(w)
    //          w为树的最大层宽，最坏O(n)
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
