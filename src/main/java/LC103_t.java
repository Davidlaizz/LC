import java.util.*;

public class LC103_t {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode buildTree(Integer[] arr){
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int idx = 1;
        while(!q.isEmpty() && idx < arr.length){
            TreeNode node = q.poll();
            if(idx < arr.length && arr[idx] != null){
                node.left = new TreeNode(arr[idx]);
                q.offer(node.left);
            }
            idx++;
            if(idx < arr.length && arr[idx] != null){
                node.right = new TreeNode(arr[idx]);
                q.offer(node.right);
            }
            idx++;
        }
        return root;
    }

    public static List<List<Integer>> travel(TreeNode root){
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean l2r = true; // 左向右遍历
        while(!q.isEmpty()){
            int size = q.size();
            LinkedList<Integer> row = new LinkedList<>();
            while(size-- > 0){
                TreeNode node = q.poll();
                if(l2r){
                    row.add(node.val);
                }else{
                    row.addFirst(node.val);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(new LinkedList<>(row));
            l2r = !l2r;
        }
        return res;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            // 非空判断
            if(str.equals("[]") || str.equals("")){
                System.out.println("[]");
                continue;
            }
            // 转化
            String trim = str.substring(1, str.length() - 1);
            String[] data = trim.split(",");
            Integer[] arr = new Integer[data.length];
            for(int i = 0; i < data.length; i++){
                arr[i] = "null".equals(data[i]) ? null : Integer.parseInt(data[i]);
            }
            TreeNode root = buildTree(arr);
            List<List<Integer>> res = travel(root);
            System.out.println(res.toString());
        }
    }
}
