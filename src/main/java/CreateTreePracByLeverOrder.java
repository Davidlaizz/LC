import java.util.LinkedList;
import java.util.Queue;
/*
    https://zhuanlan.zhihu.com/p/1894733712882653065
 */
public class CreateTreePracByLeverOrder {
    public static void main(String[] args) {
        Integer[] levelArr = {1, 2, 3, null, 4, 5};
        TreeNode root = createByLevelOrder(levelArr);
        System.out.println(root);
    }

    /**
     * 通过带空指针信息的层序遍历构建树, 核心是通过队列来逐层处理
     */
    public static TreeNode createByLevelOrder(Integer[] arr) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(arr.length == 0 || arr[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);  // 根节点入队
        int idx = 1;        // 从数组的第二个元素开始处理
        while(!queue.isEmpty() && idx < arr.length) {
            // 1) 取出队头节点
            TreeNode node = queue.poll();
            // 2）处理它的两个儿子，分别对应idx和idx+1
            if(idx < arr.length && arr[idx] != null) {  // 左儿子
                node.left = new TreeNode(arr[idx]);
                queue.offer(node.left);
            }
            idx++; // 注意不管是null还是有效值，都会视为已处理过当前位置，指针后移
            if(idx < arr.length && arr[idx] != null) {  // 右儿子
                node.right = new TreeNode(arr[idx]);
                queue.offer(node.right);
            }
            idx++;
        }
        return root;
    }

}