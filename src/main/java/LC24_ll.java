public class LC24_ll {
    // 方法: 迭代 + 哨兵节点 + 局部重连
    //   思路：每轮用pre和cur定位一对节点，交换cur与cur.next
    //   关键：pre指向待交换对的前驱，cur指向这对中的第一个节点
    //         交换后，cur变成这一对的尾节点，pre再移动到cur
    //         然后继续处理下一对
    //   复杂度：时间O(n)，空间O(1)
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
