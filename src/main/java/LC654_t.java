public class LC654_t {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildNode(nums, 0, nums.length);
    }
    //左闭右开
    public TreeNode buildNode(int[] nums, int start, int end){
        if(start >= end) return null;
        // System.out.println(start +" "+ end);
        int index = maxIndex(nums, start, end);
        //根左右
        TreeNode root = new TreeNode(nums[index]);
        root.left = buildNode(nums, start, index);
        root.right = buildNode(nums, index + 1, end);
        return root;
    }
    public int maxIndex(int[] nums, int start, int end){
        int max = Integer.MIN_VALUE;
        int index = -1;
        for(int i = start; i < end; i++){
            if(max <= nums[i]){
                max = nums[i];
                index = i;
            }
        }
        return index;
    }
}
