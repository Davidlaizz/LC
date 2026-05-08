import java.util.HashMap;
import java.util.Map;

public class LC146_ll {
    class LRUCache {
        // 方法: HashMap + 双向链表
        //   目标：在O(1)时间完成get/put，并维护最近最少使用淘汰策略
        //   结构：HashMap负责key到节点的O(1)定位，双向链表负责访问顺序维护
        //   约定：链表头部是最近使用，尾部是最久未使用
        //   get：命中后把节点移动到头部；未命中返回-1
        //   put：已存在则更新并移动到头；不存在时头插，超容则删除尾前节点
        //   复杂度：get/put平均时间O(1)，空间O(capacity)

        private Map<Integer, DLLNode> cache;
        private int capacity;
        private int curSize;
        private DLLNode head;
        private DLLNode tail;

        class DLLNode{ //双向链表节点
            int key;
            int value;
            //虚拟头尾节点
            DLLNode next;
            DLLNode pre;
            DLLNode(){}
            DLLNode(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        public LRUCache(int capacity) {
            //init
            this.capacity = capacity;
            this.curSize = 0;
            this.cache = new HashMap<>();
            this.head = new DLLNode();
            this.tail = new DLLNode();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if(cache.containsKey(key)){
                //调整缓存顺序
                DLLNode newhead = cache.get(key);
                //原链表拿出newhead
                newhead.pre.next = newhead.next;
                newhead.next.pre = newhead.pre;
                //更新到头
                head.next.pre = newhead;
                newhead.next = head.next;
                newhead.pre = head;
                head.next = newhead;

                return newhead.value;
            }else{
                return -1;
            }
        }

        public void put(int key, int value) {
            if(cache.containsKey(key)){ //存在 修改值
                DLLNode update = cache.get(key);
                update.value = value;
                get(key);
            }
            // else if(curSize == 0){ //不存在 为空
            //     this.head = new DLLNode(key, value);
            //     this.tail = head;
            //     cache.put(key, this.head);
            //     curSize++;
            // }
            else if(capacity > curSize){ // 不存在 不满
                DLLNode newhead = new DLLNode(key, value);
                newhead.next = head.next;
                head.next.pre = newhead;
                head.next = newhead;
                newhead.pre = head;

                cache.put(key, newhead);
                curSize++;
            }else{ //不存在 满了
                //踢出最后元素
                cache.remove(tail.pre.key);
                tail.pre = tail.pre.pre;
                tail.pre.next = tail;
                //添加头元素
                DLLNode newhead = new DLLNode(key, value);
                newhead.next = head.next;
                head.next.pre = newhead;
                head.next = newhead;
                newhead.pre = head;

                cache.put(key, newhead);
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


}
