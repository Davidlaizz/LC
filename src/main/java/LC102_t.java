import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LC102_t {
    // 方法: 层序遍历(BFS)
    //   思路：使用队列按层遍历二叉树，每轮处理当前层全部节点
    //   关键：每轮先记录size = q.size()，确保只弹出当前层节点
    //   收集：当前层节点值放入row，处理完后拷贝到res
    //   复杂度：时间O(n)，空间O(n)
    //          队列最坏存一整层节点
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        List<Integer> row = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        TreeNode cur = root;
        q.add(cur);
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                cur = q.remove();
                row.add(cur.val);
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }
            res.add(new ArrayList<>(row));
            row.clear();
        }
        return res;
    }
}
