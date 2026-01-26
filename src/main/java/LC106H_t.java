import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC106H_t {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 判断非空
        int n = postorder.length;
        if(n == 0)
            return null;
        int rootVal = postorder[n - 1];
        TreeNode root = new TreeNode(rootVal);
        // 可能是叶子节点
        if(n == 1)
            return root;
        // 找出中序的左右子树
        int i = 0;
        for(int item : inorder){
            if(item == rootVal){
                break;
            }
            i++;
        }
        // Arrays.copyOfRange耗时耗空间，改造成直接记录index
        // 切分中序数组
        int[] leftm = Arrays.copyOfRange(inorder, 0, i);
        int[] rightm = Arrays.copyOfRange(inorder, i + 1, n);
        // 切分后序数组
        int[] leftp = Arrays.copyOfRange(postorder, 0, i);
        int[] rightp = Arrays.copyOfRange(postorder, i, n - 1); //区间是[i, n - 1), n-1是根节点
        // 构建子树
        root.left = buildTree(leftm, leftp);
        root.right = buildTree(rightm, rightp);
        return root;
    }

    // 优化切分数组的时间
    Map<Integer, Integer> hm; // item : index
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        // 判断非空
        int n = postorder.length;
        if(n == 0)
            return null;
        hm = new HashMap<>();
        for(int i = 0; i < n; i++){
            hm.put(inorder[i], i);
        }
        return buildNode(inorder, 0 , n, postorder, 0, n);
    }
    // 左闭右开
    public TreeNode buildNode(int[] inorder, int inStart, int inEnd,
                              int[] postorder, int poStart, int poEnd){
        // 检查左闭右开
        if(inStart >= inEnd || poStart >= poEnd){
            return null;
        }
        int rootVal = postorder[poEnd - 1];
        TreeNode root = new TreeNode(rootVal);
        // // 可能是叶子节点
        // if(inEnd - inStart == 1)
        //     return root;
        // 从中序中找出根节点index
        int index = hm.get(rootVal);
        int leftSize = index - inStart;
        // 构建子树, 切分数组, 建议画图理解leftSize
        root.left = buildNode(inorder, inStart, index, postorder, poStart, poStart + leftSize);
        root.right = buildNode(inorder, index + 1, inEnd, postorder, poStart + leftSize, poEnd - 1);
        return root;
    }
}
