import java.util.HashMap;
import java.util.Map;

public class LC105_t {
    // 方法: 递归分治 + HashMap定位
    //   思路：前序第一个元素是根，中序根左侧是左子树、右侧是右子树
    //   关键：用HashMap记录中序值->索引，O(1)定位根在中序的位置
    //   过程：根据中序位置算出左子树长度leftSize，划分前序和中序区间后递归
    //   区间：统一采用左闭右开[left, right)
    //   复杂度：时间O(n)，空间O(n)
    //          额外空间来自HashMap和递归栈
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
