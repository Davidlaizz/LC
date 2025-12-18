public class LC25H_ll {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    //下面这种方法比较好理解，思路一致
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prehead = new ListNode(0, head);
        ListNode groupPre = prehead, pre;
        ListNode cur = prehead, groupNxt;
        int cnt = 0;
        while (true) {
            //找一组翻转的结尾点
            while (cnt != k) {
                if (cur.next != null) {
                    cur = cur.next;
                    cnt++;
                } else {
                    break;
                }
            }
            if (cnt == k) { //非最后一组
                cnt = 0;
            } else {
                break; //最后不满足k的一组，不转置
            }
            pre = groupPre.next;
            groupNxt = cur.next;
            // System.out.println(pre.val + " " + cur.val);
            ListNode newHead = reverse(pre, cur);
            //连接反转的链表
            groupPre.next = newHead;
            pre.next = groupNxt;
            //设置新一次循环
            groupPre = pre;
            cur = pre;
        }
        return prehead.next;
    }

    //[head, tail]
    public ListNode reverse(ListNode head, ListNode tail) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head, pre = null;
        ListNode stop = tail.next;
        while (head != stop) {
            // System.out.println(head.val);
            ListNode tmp = head.next;
            //反转
            head.next = pre;
            //迭代
            pre = head;
            head = tmp;
        }
        return tail;
    }



    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prehead = new ListNode(0, head);
        ListNode groupPre = prehead;
        while(true){
            ListNode cur = groupPre;
            int kth = k;
            //寻找每一组的末尾
            while(kth > 0 && cur != null){
                kth--;
                cur = cur.next;
            }
            System.out.println(kth);
            //最后一组可能不用处理，跳出
            if(cur == null){
                break;
            }
            //设置好参数
            // ListNode pre = groupPre.next;
            ListNode groupNxt = cur.next;
            ListNode pre = groupNxt;
            cur = groupPre.next;
            //反转
            while(cur != groupNxt){
                ListNode tmp = cur.next;
                cur.next = pre;

                pre = cur;
                cur = tmp;
            }
            //连接
            ListNode tmp = groupPre.next; //旧组的表尾
            groupPre.next = pre; //连接旧组的表头
            groupPre = tmp; //gP更新到旧组的表尾，也是新组的表头
        }
        return prehead.next;
    }

}
