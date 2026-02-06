import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC257H_t {
    //回溯
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if(root == null) return res;
        backTracking(root, path, res);
        return res;
    }
    //前序遍历
    public void backTracking(TreeNode node, List<Integer> path, List<String> res){
        path.add(node.val);//根
        if(node.left == null && node.right == null){//当前节点是叶子节点，打印路径
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < path.size() - 1; i++){//最后一个路径单独处理，不需要->
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
//            因为 String 是不可变的，+ 每次都会创建新对象；
//            StringBuilder 是可变的，拼接不会反复创建对象，性能更好。
//            String p = "";
//            for(int i = 0; i < path.size() - 1; i++){//最后一个路径单独处理，不需要->
//                p += path.get(i);
//                p += "->";
//            }
//            p += path.get(path.size() - 1);
//            res.add(p);
        }
        //左右
        if(node.left != null){
            backTracking(node.left, path, res);
            //回溯
//            path.removeLast();
            path.remove(path.size() - 1);
        }
        if(node.right != null){
            backTracking(node.right, path, res);
            //回溯
//            path.removeLast();
            path.remove(path.size() - 1);
        }
    }


    //迭代模仿前序遍历
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Object> s = new Stack<>();

        // 初始状态：先放节点，再放路径 (这样pop的时候先拿路径，再拿节点)
        s.push(root);
        s.push(root.val + "");

        while (!s.isEmpty()) {
            Object obj = s.pop();

            if (obj != null) {
                // --- 1. 这是第一次访问该节点 (相当于模板的 if node != null) ---

                // 因为没有遇到 null 标记，说明这是"路径字符串"，紧接着下面一定是"节点"
                String path = (String) obj;       // 获取路径
                TreeNode node = (TreeNode) s.pop(); // 获取节点 (成对弹出)

                // 按照 统一迭代法 的逻辑：右 -> 左 -> 中(加标记Null)

                // A. 右节点入栈
                if (node.right != null) {
                    s.push(node.right);
                    s.push(path + "->" + node.right.val);
                }

                // B. 左节点入栈
                if (node.left != null) {
                    s.push(node.left);
                    s.push(path + "->" + node.left.val);
                }

                // C. 中节点重新入栈 (等待被处理)
                s.push(node);
                s.push(path); // 路径也要带回去，不然一会儿处理的时候拿不到
                s.push(null); // 【关键】加入 null 标记

            } else {
                // --- 2. 遇到 null 了，说明该处理下面的节点了 (相当于 unify 模板的 else) ---

                // 弹出 null 下面的成对数据
                String path = (String) s.pop();
                TreeNode node = (TreeNode) s.pop();

                // 真正的处理逻辑：判断是不是叶子
                if (node.left == null && node.right == null) {
                    res.add(path);
                }
            }
        }
        return res;
    }
}
