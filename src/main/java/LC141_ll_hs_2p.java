import java.util.HashSet;
import java.util.Set;

public class LC141_ll_hs_2p {
    // 方法1: HashSet
    //   思路：遍历链表并记录访问过的节点地址
    //   判定：若当前节点已在Set中，说明再次访问到同一节点，即有环
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法2: 快慢指针(推荐)
    //   思路：slow每次走1步，fast每次走2步
    //   结论：若有环，fast最终会在环内追上slow
    //         若无环，fast或fast.next会先到null
    //   复杂度：时间O(n)，空间O(1)
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

    public boolean hasCycle2(ListNode head) {
        if(head == null) return false;
        // 快慢指针
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
