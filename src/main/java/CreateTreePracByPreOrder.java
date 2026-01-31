import java.util.LinkedList;
import java.util.Queue;

/*
    https://zhuanlan.zhihu.com/p/1894733712882653065
 */
public class CreateTreePracByPreOrder {
    public static void main(String[] args) {
        Integer[] preArr = {1, 2, null, 4, null, null, 3, 5}; // 带空指针信息的前序序列，末尾null可以不写
        TreeNode root = createByPreorder(preArr);
        System.out.println(root); // 可以在这句打上断点，然后debug看root的结构是否符合预期
        levelTrave(root);
    }

    /**
     * 通过带空指针信息的前序遍历构建树，本质是递归性的创建arr[idx]所在的子树，然后返回这棵子树的根节点
     */
    static int idx = 0; // 全局量，标识当前处理到的数组位置（如果要多次使用记得清零）
    public static TreeNode createByPreorder(Integer[] arr) {
        if(idx >= arr.length || arr[idx] == null) { // 如果arr[idx]越界或者当前位置是null，那就返回null
            idx++;
            return null;
        }
        TreeNode node = new TreeNode(arr[idx++]);   // 创建当前子树的根
        node.left = createByPreorder(arr);          // 递归创建左右子树
        node.right = createByPreorder(arr);
        return node;
    }

    public static void levelTrave(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
//            // 关键标记：假设下一层全是 Null
//            boolean hasNextLevelNode = false;
            while(size-- > 0){
                TreeNode cur = q.poll();
                if (cur != null) {
                    System.out.print(cur.val + " ");
                    q.offer(cur.left);
                    q.offer(cur.right);
//                    // 侦查：如果发现任何一个孩子是真实存在的，说明下一层不是全空的
//                    if (cur.left != null || cur.right != null) {
//                        hasNextLevelNode = true;
//                    }
                }else{
                    System.out.print("Null ");
                }
            }
            System.out.println();
//            // 刹车机制：
//            // 如果遍历完这一层，发现下一层没有一个真实节点（全是占位的 Null）
//            // 直接跳出循环，不再处理下一层
//            if (!hasNextLevelNode) {
//                break;
//            }
        }
    }
}