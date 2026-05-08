import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class LC101_t {
    // 方法1: 递归镜像对比
    //   思路：同时比较两棵子树left/right是否互为镜像
    //   条件：节点值相等，且left.left对right.right、left.right对right.left
    //   终止：两节点都为空返回true；一空一非空返回false
    //   复杂度：时间O(n)，空间O(h)
    //
    // 方法2: 队列迭代(成对入队)
    //   思路：每次成对取出左右镜像位置节点进行比较
    //   入队顺序：l.left与r.right、l.right与r.left
    //   关键：保持“镜像节点成对出现”的队列结构
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法3: 队列迭代(ArrayDeque变体)
    //   思路：与方法2一致，差别在于对null节点采用显式分支判断
    //   注意：ArrayDeque不允许直接存null，因此需要先判空再入队
    //   复杂度：时间O(n)，空间O(n)
    //递归
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }
    public boolean check(TreeNode left, TreeNode right){
        if(left != null && right != null){ //都存在
            if(left.val == right.val){
                return check(left.left, right.right) && check(left.right, right.left);
            }else
                return false;
        }
        else if(left == null && right == null) //都不存在
            return true;
            // if(left != null || right != null)
        else //一个存在，一个不存在
            return false;
    }

    //迭代 队列/栈
    public boolean isSymmetric2(TreeNode root) {
        if(root == null)
            return true;

        //迭代 层序遍历 每层最左最右节点先对比
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);
        while(!q.isEmpty()){
            TreeNode l = q.poll();
            TreeNode r = q.poll();
            //为空，对称
            if(l == null && r == null)
                continue;
            //不为空，判断是否相等。相等==对称，继续层次遍历，添加节点
            if(l == null || r == null || l.val != r.val){
                return false;
            }else{
                q.offer(l.left);
                q.offer(r.right);

                q.offer(l.right);
                q.offer(r.left);
            }
        }
        return true;
    }

    //也是队列迭代 ArrayDeque要求不能为空 先判断为空再offer
    public boolean isSymmetric3(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null) //都不存在
            return true;
        if (root.left == null || root.right == null) //只存在一个
            return false;
        //都存在的情况
        //迭代 层序遍历 每层最左最右节点先对比
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root.left);
        q.offer(root.right);
        while (!q.isEmpty()) {
            TreeNode l = q.poll();
            TreeNode r = q.poll();
            if (l.val != r.val) {
                return false;
            } else {
                if (l.left != null && r.right != null){ //都存在
                    q.offer(l.left);
                    q.offer(r.right);
                }
                else if (l.left != null || r.right != null) //只存在一个,剪枝
                    return false;

                if (l.right != null && r.left != null){ //都存在
                    q.offer(l.right);
                    q.offer(r.left);
                }
                else if (l.right != null || r.left != null) //只存在一个,剪枝
                    return false;

            }
        }
        return true;
    }
}
