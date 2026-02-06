import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC199_t {
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
