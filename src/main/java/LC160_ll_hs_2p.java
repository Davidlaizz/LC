import java.util.HashSet;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LC160_ll_hs_2p {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> hsa = new HashSet<>();
        ListNode it = headA;
        while(it != null){
            hsa.add(it);
            it = it.next;
        }

        it = headB;
        while(it != null){
            if(hsa.contains(it)){
                return it;
            }
            it = it.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode h1 = headA, h2 = headB;
        while(h1 != h2){ //匹配或者为空
            if(h1 != null)
                h1 = h1.next;
            else
                h1 = headB;
            if(h2 != null)
                h2 = h2.next;
            else
                h2 = headA;
        }
        return h1;
    }
}
