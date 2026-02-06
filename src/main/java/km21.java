

import java.util.*;
/*
    https://kamacoder.com/problempage.php?pid=1020
 */
public class km21{
    public static void main(String[] args){
        km21 ts = new km21();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String preorder = sc.next();
            String inorder = sc.next();
            char[] preorderChar = preorder.toCharArray();
            char[] inorderChar = inorder.toCharArray();
            hm = new HashMap<>();
            for(int i = 0; i < inorderChar.length; i++){
                hm.put(inorderChar[i], i);
            }
            int n = preorderChar.length;
            TreeNode root = ts.buildTree(inorderChar, 0, n,
                    preorderChar, 0 , n);

            //有更简单的方法，64行
            //ts.postorder(root);
            System.out.println("");
        }

    }

    class TreeNode{
        char val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}
        public TreeNode(char val){
            this.val = val;
        }
        public TreeNode(char val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static Map<Character, Integer> hm; // item : index
    // 左闭右开
    public TreeNode buildTree(char[] inorder, int inStart, int inEnd,
                              char[] preorder, int preStart, int preEnd){
        // 检查左闭右开
        if(inStart >= inEnd || preStart >= preEnd){
            return null;
        }
        char rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        // 从中序中找出根节点index
        int index = hm.get(rootVal);
        int leftSize = index - inStart;
        // 构建子树, 切分数组, 建议画图理解leftSize
        root.left = buildTree(inorder, inStart, index, preorder, preStart + 1, preStart + leftSize + 1);
        root.right = buildTree(inorder, index + 1, inEnd, preorder, preStart + leftSize + 1, preEnd);

        // 这里直接输出就是后序遍历 不用造完树再后序树
        System.out.print(rootVal);

        return root;
    }

    public void postorder(TreeNode root){
        if(root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val);
    }
}