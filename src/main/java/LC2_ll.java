public class LC2_ll {
    // 方法: 链表模拟竖式加法
    //   思路：同步遍历l1和l2，逐位相加并维护进位carry
    //   细节：某一条链表先结束时，该位按0处理
    //   关键：循环条件包含carry != 0，保证最后进位不会丢失
    //   构造：用哨兵head统一处理结果链表头节点
    //   复杂度：时间O(max(m,n))，空间O(max(m,n))
    //          额外空间主要是结果链表节点
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
