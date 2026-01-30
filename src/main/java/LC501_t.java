import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC501_t {
    public int[] findMode(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        List<Integer> mode = new ArrayList<>();
        s.push(root);
        TreeNode pre = null;
        int maxCnt = 0;
        int curCnt = 0;
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
                // 初始化
                if(pre == null){
                    curCnt = 1;
                    maxCnt = curCnt;
                    mode.add(cur.val);
                }
                // 和中序的前一个元素对比
                if(pre != null && pre.val == cur.val){
                    curCnt++;
                    if(curCnt == maxCnt){
                        mode.add(cur.val);
                    }
                    if(curCnt > maxCnt){
                        mode.clear();
                        maxCnt = curCnt;
                        mode.add(cur.val);
                    }
                }
                if(pre != null && pre.val != cur.val){
                    curCnt = 1;
                    if(curCnt == maxCnt){
                        mode.add(cur.val);
                    }
                }
                // 记录本次节点作为历史节点
                pre = cur;
            }
        }
        // List -> int
        int res[] = new int[mode.size()];
        for(int i = 0; i < mode.size(); i++){
            res[i] = mode.get(i);
        }
        return res;
    }

    List<Integer> mode = new ArrayList<>();
    TreeNode pre = null;
    int maxCnt = 0;
    int curCnt = 0;
    public int[] findMode2(TreeNode root) {
        inorder(root);
        // List -> int
        int res[] = new int[mode.size()];
        for(int i = 0; i < mode.size(); i++){
            res[i] = mode.get(i);
        }
        return res;
    }
    public void inorder(TreeNode cur){
        if(cur == null)
            return;
        inorder(cur.left);

        // 初始化
        if(pre == null){
            curCnt = 1;
            maxCnt = curCnt;
            mode.add(cur.val);
        }
        // 和中序的前一个元素对比
        if(pre != null && pre.val == cur.val){
            curCnt++;
            if(curCnt == maxCnt){
                mode.add(cur.val);
            }
            if(curCnt > maxCnt){
                mode.clear();
                maxCnt = curCnt;
                mode.add(cur.val);
            }
        }
        if(pre != null && pre.val != cur.val){
            curCnt = 1;
            if(curCnt == maxCnt){
                mode.add(cur.val);
            }
        }
        // 记录本次节点作为历史节点
        pre = cur;

        inorder(cur.right);
    }
}
