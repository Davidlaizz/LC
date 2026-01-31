import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LC297H_t {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    TreeNode cur = q.poll();
                    if (cur == null) {
                        sb.append("null,");
                    } else {
                        sb.append(cur.val).append(",");
                        q.offer(cur.left);
                        q.offer(cur.right);
                    }
                }
            }
            String s = sb.toString().substring(0, sb.length() - 1 );
            // System.out.println(s);
            return "[" + s + "]";
        }

        // Decodes your encoded data to tree.

        public TreeNode deserialize(String data) {
            int idx = 0;
            // 排除null
            if (data == null || idx >= data.length()) {
                // System.out.println(data + " 1");
                return null;
            }
            // 其他都是"[1,2,null]" 排除第零位和最后一位
            data = data.substring(1, data.length() - 1);
            String[] arr = data.split(",");
            Deque<TreeNode> q = new ArrayDeque<>();
            // 初始化根
            String rootS = arr[idx];
            idx++;
            int rootVal = Integer.parseInt(rootS);
            TreeNode root = new TreeNode(rootVal);
            q.offer(root);
            // 队列模拟层次遍历
            while (!q.isEmpty() && idx < arr.length) {
                int size = q.size();
                while (size-- > 0) {
                    TreeNode node = q.poll();
                    if (idx < data.length() && !arr[idx].equals("null")) {
                        node.left = new TreeNode(Integer.parseInt(arr[idx]));
                        q.offer(node.left);
                    }
                    idx++;
                    if (idx < data.length() && !arr[idx].equals("null")) {
                        node.right = new TreeNode(Integer.parseInt(arr[idx]));
                        q.offer(node.right);
                    }
                    idx++;
                }
            }
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}
