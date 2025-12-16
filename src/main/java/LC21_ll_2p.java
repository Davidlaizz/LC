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