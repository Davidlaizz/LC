import java.util.ArrayDeque;
import java.util.Queue;

public class LC108_t {
    //迭代：nums有序，根据下标选择mid，尽可能填满左右子树
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    //index : [left, right]
    public TreeNode build(int[] nums, int left, int right){
        if(left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    //迭代：用三个队列实现根左右
    public TreeNode sortedArrayToBST2(int[] nums) {
        Queue<TreeNode> nodeQ = new ArrayDeque<>();
        Queue<Integer> leftQ = new ArrayDeque<>(); //下标
        Queue<Integer> rightQ = new ArrayDeque<>(); //下标
        TreeNode root = new TreeNode(nums[(0 + nums.length - 1) / 2]);
        nodeQ.offer(root);
        leftQ.offer(0);
        rightQ.offer(nums.length - 1);
        while (!nodeQ.isEmpty()) { //不空表示有节点,尝试处理左右子树
            TreeNode node = nodeQ.poll();
            int l = leftQ.poll();
            int r = rightQ.poll();
            int mid = (l + r) / 2;
            //处理左右子树
            if (l <= mid - 1) { //是否有左子树
                int mmid = (l + mid - 1) / 2;
                node.left = new TreeNode(nums[mmid]); //先连接，保证有这个节点，后续循环再赋值
                nodeQ.offer(node.left);
                leftQ.offer(l);
                rightQ.offer(mid - 1);
            }
            if (r >= mid + 1) {
                int mmid = (r + mid + 1) / 2;
                node.right = new TreeNode(nums[mmid]);
                nodeQ.offer(node.right);
                leftQ.offer(mid + 1);
                rightQ.offer(r);
            }
        }
        return root;
    }
}
