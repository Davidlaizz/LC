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
    // 方法1: 递归回溯计数
    //   思路：递归到链表末尾后回溯，用cnt记录“倒数第几个”
    //   删除：当回溯到待删节点前驱时执行cur.next = cur.next.next
    //   特点：思路直观，但依赖递归栈和全局计数变量
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法2: 快慢指针(推荐)
    //   思路：fast先走n+1步，让slow停在待删节点前驱
    //   过程：随后fast/slow同步前进，直到fast到null
    //   删除：slow.next = slow.next.next
    //   复杂度：时间O(n)，空间O(1)
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
        // 先让fast走n+1步，建立与slow的固定间距
        // 这样当fast到达null时，slow正好停在待删节点的前一个节点
        for(int i = 0; i <= n; i++){
            fast = fast.next;
        }

        // fast和slow同步前进，保持间距不变
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        // 删除slow后面的节点（即倒数第n个节点）
        slow.next = slow.next.next;
        return prehead.next;
    }
}
