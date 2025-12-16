import java.util.HashSet;

public class LC141_ll_hs_2p {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> hs = new HashSet<>();
        ListNode it = head;
        while(it != null){
            if(hs.contains(it)){
                return true;
            }else{
                hs.add(it);
            }
            it = it.next;
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null){
            if(slow == fast)
                return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
