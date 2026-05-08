import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC23H_ll_ms {
    // 方法: 两两归并(分治思想)
    //   思路：把k个有序链表按相邻两两合并，每轮把数量约减半
    //   过程：第1轮k->k/2，第2轮k/2->k/4 ... 直到只剩1条链表
    //   细节：当轮若链表个数为奇数，最后一条直接进入下一轮
    //   子问题：两链表合并使用LC21的双指针合并
    //   复杂度：时间O(N log k)，空间O(k)
    //          N为总节点数，k为链表个数
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        //转成List方便动态扩容
        List<ListNode> list = Arrays.asList(lists);
        while(list.size() >= 2){ //22一组合并
            List<ListNode> tmp = new ArrayList<>();
            for(int i = 0; i < list.size(); i += 2){
                ListNode l1 = list.get(i);
                if(i + 1 >= list.size()){//l2不一定存在
                    tmp.add(l1);
                    break;
                };
                ListNode l2 = list.get(i + 1);
                ListNode merge = mergeList(l1, l2);
                tmp.add(merge);
            }
            list = tmp;
        }
        return list.get(0);
    }

    public ListNode mergeList(ListNode l1, ListNode l2){
        ListNode preehead = new ListNode(0);
        ListNode cur = preehead;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                cur.next = l2;
                l2 = l2.next;
            }else{
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if(l1 != null){
            cur.next = l1;
        }else{
            cur.next = l2;
        }
        return preehead.next;
    }
}

