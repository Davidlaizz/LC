import java.util.ArrayList;
import java.util.List;

public class LC98_t {
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



}
