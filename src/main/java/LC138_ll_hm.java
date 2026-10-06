import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

public class LC138_ll_hm {
    // 方法1: HashMap两遍遍历
    //   思路：先遍历原链表，为每个旧节点创建新节点并建立映射(old -> new)
    //   处理：第二遍根据映射补全new.next和new.random指向
    //   特点：实现直观，next/random都能O(1)定位对应新节点
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法2: 递归 + HashMap记忆化
    //   思路：定义copy(node)：返回node对应的新节点
    //   处理：若节点未复制则先建新节点并入map，再递归复制next/random
    //   关键：map用于去重和防止random形成环导致重复构建
    //   复杂度：时间O(n)，空间O(n)
    //          额外包含递归调用栈最坏O(n)
    // 方法1 和 2 本质是一种方法
    //
    // 方法3: 迭代 + 节点拆分(原地穿插)
    //   遍历1：插入拷贝节点（交错链表），A -> A' -> B -> B' -> C -> C'
    //   遍历2：设置拷贝节点的 random，利用old.random.next直接设置copy.random
    //   遍历3：拆分交错链，恢复旧链并提取新链
    //   特点：不使用HashMap，靠邻接关系建立旧新映射
    //   复杂度：时间O(n)，空间O(1)

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

    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }

}

