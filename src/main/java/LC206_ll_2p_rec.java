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
