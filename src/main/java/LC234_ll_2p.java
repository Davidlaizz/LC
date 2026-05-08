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
    // 方法: 快慢指针 + 反转后半链表
    //   思路：先用快慢指针找到前半段尾节点slow
    //   处理：反转slow后半部分，再与前半部分逐节点比较
    //   细节：奇数长度时，前半会比后半多一个中点元素，不影响比较
    //   终止：后半链表比较完(second == null)且无差异则为回文
    //   复杂度：时间O(n)，空间O(1)
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
