import java.util.HashMap;
import java.util.Map;

public class LC105_t {
    Map<Integer, Integer> hm = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // val : index 方便查找中序
        for(int i = 0; i < inorder.length; i++){
            hm.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length, inorder, 0, preorder.length);
    }
    //左闭右开
    public TreeNode build(int[] preorder, int prstart, int prend,
                          int[] inorder,  int instart, int inend){
        if(prstart >= prend || instart >= inend)
            return null;
        // 找根节点
        int rootVal = preorder[prstart];
        TreeNode root = new TreeNode(rootVal);
        // 根据中序分左右
        int inidx = hm.get(rootVal);
        int leftSize = inidx - instart;
        root.left = build(preorder, prstart + 1, prstart + 1 + leftSize,
                inorder,  instart,     inidx);
        root.right = build(preorder, prstart + 1 + leftSize, prend,
                inorder,  inidx + 1,   inend);
        return root;
    }
}
