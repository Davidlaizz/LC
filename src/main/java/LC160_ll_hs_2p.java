import java.util.HashSet;
import java.util.Set;

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
    // 方法1: HashSet
    //   思路：先遍历链表A并记录所有节点地址，再遍历链表B
    //   判定：B中第一个出现在Set里的节点，就是相交起点
    //   特点：实现直观，依赖额外集合
    //   复杂度：时间O(m+n)，空间O(m)
    //
    // 方法2: 双指针(推荐)
    //   思路：h1先从headA出发，走到null后切到headB继续走
    //         h2先从headB出发，走到null后切到headA继续走
    //   结论：两者总路程相同，若有交点会在交点相遇
    //         若无交点，最终都会为null并相遇
    //   复杂度：时间O(m+n)，空间O(1)
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
