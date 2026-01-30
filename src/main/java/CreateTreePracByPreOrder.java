/*
    https://zhuanlan.zhihu.com/p/1894733712882653065
 */
public class CreateTreePracByPreOrder {
    public static void main(String[] args) {
        Integer[] preArr = {1, 2, null, 4, null, null, 3, 5}; // 带空指针信息的前序序列，末尾null可以不写
        TreeNode root = createByPreorder(preArr);
        System.out.println(root); // 可以在这句打上断点，然后debug看root的结构是否符合预期
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
}