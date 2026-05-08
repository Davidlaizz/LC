/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class LC21_ll_2p {
    // 方法: 双指针迭代 + 哨兵节点
    //   思路：list1/list2分别指向两条有序链表当前节点，较小者接到结果链表尾部
    //   关键：使用prehead简化头节点处理，it始终指向结果链表尾
    //   收尾：任一链表先空后，将另一条剩余部分整体接到尾部
    //   复杂度：时间O(m+n)，空间O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode prehead = new ListNode(-1);
        ListNode it = prehead;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                it.next = list1;
                list1 = list1.next;
                it = it.next;
            }else{
                it.next = list2;
                list2 = list2.next;
                it = it.next;
            }
        }
        if(list1 == null){
            it.next = list2;
        }
        if(list2 == null){
            it.next = list1;
        }
        return prehead.next;
    }
}