import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LC102_t {
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
