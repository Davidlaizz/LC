import java.util.HashSet;
import java.util.Set;
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LC142_ll_hs_2p {
    // 方法1: HashSet
    //   思路：遍历链表并记录访问过的节点地址
    //   判定：首次重复访问到的节点就是入环点
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法2: 快慢指针 + 数学结论(推荐)
    //   第一步：快慢指针判断是否有环，并在环内相遇
    //   第二步：一个指针从head出发，另一个从相遇点出发，同速前进
    //   结论：两者再次相遇的位置就是入环点
    //   复杂度：时间O(n)，空间O(1)
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> hs = new HashSet<>();
        ListNode it = head;
        while(it != null){
            if(hs.contains(it)){
                return it;
            }else{
                hs.add(it);
            }
            it = it.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        // 快慢指针
        //推理出a = b + nc, 因为这个推理，所以fast = head
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }

        if(slow != fast){
            // 无环
            return null;
        }
        else{
            //相遇，有环，找pos
            fast = head;
            while(fast != slow){
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }
}
