public class LC24_ll {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode prehead = new ListNode(0, head);
        ListNode pre = prehead, cur = head;
        while(cur != null && cur.next != null){
            //交换cur 和cur.next结点，画图想
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = cur;

            pre = cur;
            cur = cur.next;
        }
        return prehead.next;
    }
}
