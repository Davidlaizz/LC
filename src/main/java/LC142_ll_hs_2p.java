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

    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
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
