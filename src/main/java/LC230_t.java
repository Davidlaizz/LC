public class LC230_t {
    // 方法1: 中序遍历计数(当前实现)
    //   思路：BST中序遍历是升序，第k次访问到的节点就是第k小
    //   过程：递归中序(left-root-right)，用count记录访问序号
    //   剪枝：当count == k时记录答案并返回
    //   复杂度：时间O(h + k)~O(n)，空间O(h)
    //          h为树高，最坏退化为O(n)
    //
    // 方法2: 迭代中序(可选)
    //   思路：用栈模拟中序遍历，每弹出一个节点count++
    //   判定：当count == k时直接返回节点值
    //   复杂度：时间O(h + k)~O(n)，空间O(h)
    int count = 0;
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {
        // 遍历
        // int[] list = new int[10e4];
        int[] val = new int[1];
        inOrder(root, k, val);
        return val[0];
    }
    private void inOrder(TreeNode root, int k, int[] res){
        if(root == null){
            return;
        }
        inOrder(root.left, k, res);
        count++;
        if(k == count){
            // System.out.println(root.val);
            res[0] =  root.val;
            return;
        }
        inOrder(root.right, k, res);
        return;
    }

    // 迭代：null标记模板（中序 左根右）
    public int kthSmallest2(TreeNode root, int k) {
        java.util.Stack<TreeNode> s = new java.util.Stack<>();
        s.push(root);
        int cnt = 0;
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
                cnt++;
                if(cnt == k)
                    return cur.val;
            }
        }
        return -1;
    }
}
