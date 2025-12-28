import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

public class LC138_ll {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        Map<Node, Node> hm = new HashMap<>();
        Node cur = head;
        //预热到hashmap
        while(cur != null){
            Node newHead = new Node(cur.val);
            hm.put(cur, newHead);
            cur = cur.next;
        }

        cur = head;
        //从hm中读取，空间换时间
        while(cur != null){
            Node newHead = hm.get(cur);
            newHead.next = hm.get(cur.next);
            newHead.random = hm.get(cur.random);
            cur = cur.next;
        }
        return hm.get(head);
    }


    Map<Node, Node> hm = new HashMap<>();
    public Node copyRandomList2(Node head) {
        if(head == null)
            return null;

        while(!hm.containsKey(head)){ //不存在 等价于 先遍历一遍老链表，预热hm
            Node newHead = new Node(head.val);
            hm.put(head, newHead);
            newHead.next = copyRandomList(head.next); //先递归到底，从前往后连接next
            newHead.random = copyRandomList(head.random);//再返回递归，从后到前连接next
        }
        return hm.get(head);
    }

}
