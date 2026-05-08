import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC199_t {
    // 方法1: 层序遍历(BFS)
    //   思路：按层遍历，每层只记录“最先看到的右侧节点”
    //   做法：当前实现按“右->左”顺序入队，因此每层第一个出队即右视图节点
    //   复杂度：时间O(n)，空间O(w)
    //          w为最大层宽，最坏O(n)
    //
    // 方法2: 先右后左DFS
    //   思路：按深度优先遍历，始终先访问右子树
    //   判定：当dep首次到达某层时，当前节点就是该层右视图节点
    //   复杂度：时间O(n)，空间O(h)
    //          h为树高，递归栈最坏O(n)
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            int first = size;
            //从右往左的层序遍历
            while(size > 0){
                TreeNode node = q.poll();
                //第一个就是最右边节点
                if(size == first){
                    res.add(node.val);
                }
                if(node.right != null)
                    q.offer(node.right);
                if(node.left != null)
                    q.offer(node.left);
                size--;
            }
        }
        return res;
    }

    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 1);
        return res;
    }
    public void dfs(TreeNode root, int dep){
        if(root == null)
            return;
        if(dep > res.size()){
            res.add(root.val);
        }
        dfs(root.right, dep + 1);
        dfs(root.left, dep + 1);
    }
}
