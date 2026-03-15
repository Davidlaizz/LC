public class LC234_ll_2p {
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
    public boolean isPalindrome(ListNode head) {
        //找出中心位置
        ListNode slow = head, fast = head.next;
        //快慢指针
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //翻转后半 LC206
        ListNode second = reverse(slow.next);
        slow.next = null; //断开链表
        //对比链表，前半链表可能比后半链表大1（奇数时）
        ListNode first = head;
        while(second != null){
            if(second.val != first.val){
                return false;
            }
            second = second.next;
            first = first.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head){
        ListNode pre = null, cur = head;
        while(cur != null){
            ListNode temp = cur.next;

            cur.next = pre;

            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
