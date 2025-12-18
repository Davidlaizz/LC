public class LC2_ll {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        int carry = 0;
        //carry表示进位，即使l1,l2为空也要进位
        while(l1 != null || l2 != null || carry != 0){
            int v1 = 0, v2 = 0;
            if(l1 != null){
                v1 = l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                v2 = l2.val;
                l2 = l2.next;
            }
            int res = v1 + v2 + carry;
            cur.next = new ListNode(res % 10);
            cur = cur.next;
            carry = res / 10;
        }
        return head.next;
    }
}
