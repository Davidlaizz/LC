import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC98_t {
    // 方法1: 递归中序 + 收集数组
    //   思路：BST中序遍历结果应严格递增
    //   处理：先中序收集到res，再线性检查是否存在逆序
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法2: 迭代中序 + 前驱比较
    //   思路：中序遍历过程中，当前值必须大于前一个访问值pre
    //   优势：无需额外数组，边遍历边判断
    //   复杂度：时间O(n)，空间O(h)
    //
    // 方法3: 递归区间约束(推荐)
    //   思路：每个节点必须落在(lower, upper)开区间内
    //   递归：左子树上界收紧为root.val，右子树下界收紧为root.val
    //   关键：用long边界避免int极值比较问题
    //   复杂度：时间O(n)，空间O(h)
    //第一想法：平衡二叉树中序遍历是严格递增
    List<Integer> res = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        inorder(root);
        if(res.size() == 1) return true;
        // System.out.println(res);
        for(int i = 0; i < res.size() - 1; i++){
            if(res.get(i) >= res.get(i + 1))
                return false;
        }
        return true;
    }
    public void inorder(TreeNode root){
        if(root == null)
            return;
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }

    public boolean isValidBST2(TreeNode root) {
        // 迭代 中序遍历 出栈左根右小到大
        if(root == null) return true;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode pre = null;
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            if(node != null){
                if(node.right != null)
                    s.push(node.right);
                s.push(node);
                s.push(null);
                if(node.left != null)
                    s.push(node.left);
            }else{
                TreeNode cur = s.pop();
                if(pre != null && pre.val >= cur.val)
                    return false;
                // 记录本次节点作为历史节点
                pre = cur;
            }
        }
        return true;
    }

    public boolean isValidBST3(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    // 判断root满足(lower, upper)
    public boolean check(TreeNode root, long lower, long upper){
        if(root == null) return true;
        if(lower > upper) return false;
        //根
        if(lower >= root.val || upper <= root.val) return false;
        //左右
        boolean l = check(root.left, lower, root.val);
        if(!l) return l;
        boolean r = check(root.right, root.val, upper);
        return r;
    }
}
