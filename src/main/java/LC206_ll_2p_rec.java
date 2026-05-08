public class LC206_ll_2p_rec {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    // 方法1: 双指针迭代
    //   思路：用pre和cur逐节点反转next指向
    //   过程：先暂存cur.next，再执行cur.next = pre，然后整体前移
    //   结果：cur走到null时，pre即为新头节点
    //   复杂度：时间O(n)，空间O(1)
    //
    // 方法2: 递归
    //   思路：参数(pre, cur)表示已反转部分头和待处理当前节点
    //   过程：每层先反转cur.next指向pre，再递归处理后续节点
    //   终止：cur == null时返回pre（新头节点）
    //   复杂度：时间O(n)，空间O(n)
    //          额外空间来自递归调用栈
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            //反转
            cur.next = pre;
            //迭代
            pre = cur;
            cur = temp;
        }
        return pre;
    }


    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    public ListNode reverse(ListNode pre, ListNode cur){
        if(cur == null)
            return pre;
        ListNode temp = cur.next;
        //反转
        cur.next = pre;
        //递归
        return reverse(cur, temp);

    }
}
