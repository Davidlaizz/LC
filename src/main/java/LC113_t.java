import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC113_t {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if(root == null) return res;
        path.add(root.val);
        backTracking(root, targetSum - root.val, path, res);
        return res;
    }
    public void backTracking(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res){
        if(root.left == null && root.right == null){
            if(targetSum == 0){
                res.add(new ArrayList<>(path));
                return;
            }
            else
                return;
        }
        if(root.left != null){
            path.add(root.left.val);
            backTracking(root.left, targetSum - root.left.val, path, res);
//            path.removeLast();
            path.remove(path.size() - 1);
        }
        if(root.right != null){
            path.add(root.right.val);
            backTracking(root.right, targetSum - root.right.val, path, res);
//            path.removeLast();
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        Stack<List<Integer>> sp = new Stack<>();
        Stack<Integer> si = new Stack<>();

        s.push(root);
        List<Integer> initPath = new ArrayList<>();
        initPath.add(root.val);
        sp.push(initPath);
        si.push(root.val);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            Integer value = si.pop();
            List<Integer> path = sp.pop();
            if(node != null){
                s.push(node);
                s.push(null);
                si.push(value);
                si.push(null);
                sp.push(path);
                sp.push(null);
                if(node.left != null){
                    s.push(node.left);
                    path.add(node.left.val);
                    sp.push(new ArrayList<>(path));
//                    path.removeLast();
                    path.remove(path.size() - 1);
                    si.push(value + node.left.val);
                }
                if(node.right != null){
                    s.push(node.right);
                    path.add(node.right.val);
                    sp.push(new ArrayList<>(path));
//                    path.removeLast();
                    path.remove(path.size() - 1);
                    si.push(value + node.right.val);
                }
            }else{
                TreeNode cur = s.pop();
                Integer sum = si.pop();
                List<Integer> p = sp.pop();
                if(cur.left == null && cur.right == null && sum == targetSum)
                    res.add(new ArrayList<>(p));
            }
        }
        return res;
    }
}
