public class LC148_ll_ms {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //中点拆分
        ListNode first = head;
        ListNode second = slow.next;
        slow.next = null;
        //先排序字串
        first = sortList(first);
        second = sortList(second);
        //合并排序好的字串
        return mergeList(first, second);
    }

    public ListNode mergeList(ListNode left, ListNode right) {
        ListNode prehead = new ListNode(0, left);
        ListNode cur = prehead;
        while (left != null && right != null) {
            if (left.val > right.val) {
                cur.next = right;
                right = right.next;
            } else {
                cur.next = left;
                left = left.next;
            }
            cur = cur.next;
        }
        //补齐另一部分
        if (left != null) {
            cur.next = left;
        } else {
            cur.next = right;
        }
        return prehead.next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
