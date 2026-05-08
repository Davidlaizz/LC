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

    // 方法1: 分组定位 + 区间反转函数
    //   思路：每次先找到长度为k的一组[start, end]
    //   处理：调用reverse(start, end)反转该组，再与前后链表重新连接
    //   终止：剩余节点不足k个时停止，保持原顺序
    //   复杂度：时间O(n)，空间O(1)
    //
    // 方法2: 分组定位 + 组内原地迭代反转
    //   思路：先找到组尾cur和组后节点groupNxt
    //   注意1：必须先确认当前组节点数是否达到k，不足k直接结束
    //   注意2：反转前先保存groupNxt，避免改指针后丢失后续链表
    //   注意3：连接顺序要正确
    //         groupPre.next接新组头，旧组头(新组尾)接groupNxt
    //         最后groupPre移动到旧组头，进入下一组
    //   复杂度：时间O(n)，空间O(1)

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



    public ListNode reverseKGroup2(ListNode head, int k) {
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
//            System.out.println(kth);
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
