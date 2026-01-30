public class LC19_ll_rec_2p {
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
    int cnt = 0;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prehead = new ListNode();
        prehead.next = head;
        recDel(prehead, n);
        return prehead.next;

    }
    public void recDel(ListNode cur, int n){
        //表尾，计数
        if(cur == null){
            cnt++;
            return;
        }
        //往后面递归
        recDel(cur.next, n);
        //回调的过程中 判断是否是 删除节点的前一个节点
        if(cnt == n + 1){
            cur.next = cur.next.next;
        }
        cnt++;
        return;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode prehead = new ListNode(0, head);
        //快慢指针
        ListNode fast = prehead, slow = prehead;
        //让慢指针指向删除元素的前一个节点
        for(int i = 0; i <= n; i++){
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        //删除
        slow.next = slow.next.next;
        return prehead.next;
    }
}
