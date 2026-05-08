public class LC148_ll_ms {
    // 方法1: 链表归并排序(自顶向下递归)
    //   思路：用快慢指针找中点并断开，递归排序左右子链表
    //   合并：将两个有序链表按升序归并为一个有序链表
    //   关键：slow.next = null完成拆分，避免递归时链表相互连通
    //   复杂度：时间O(nlogn)，空间O(logn)
    //          额外空间来自递归调用栈
    //
    // 方法2: 链表归并排序(自底向上迭代)
    //   思路：按子链长度subLen = 1,2,4,8... 迭代分组并两两归并
    //   过程：每轮把链表切成若干段长度subLen的有序子链，再合并成2*subLen
    //   终止：当subLen >= length时，整体已有序
    //   特点：不使用递归，适合进阶要求的常数额外空间
    //   复杂度：时间O(nlogn)，空间O(1)
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

