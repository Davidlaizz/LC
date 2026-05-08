public class LC208Tire_t {
    // 方法: 前缀树(Trie/字典树)
    //   结构：每个节点包含26个子节点指针(children[])和结束标记(end)
    //   insert：逐字符向下走，没有就创建，末尾节点end置true
    //   search：逐字符向下走，中途断开返回false，到达末尾检查end
    //   startsWith：逐字符向下走，中途断开返回false，不检查end
    //   特点：适合大量字符串的快速前缀匹配
    //   复杂度：insert/search/startsWith 时间O(L)，空间O(26*L)
    //          L为字符串长度
    class Trie {
        class Node {
            Node[] children;
            boolean end;

            public Node() {
                children = new Node[26];
                end = false;
            }
        }

        Node head;

        public Trie() {
            head = new Node();
        }

        public void insert(String word) {
            Node cur = head;
            for (char c : word.toCharArray()) {
                // 没有就创建
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Node();
                }
                // 往下走
                cur = cur.children[c - 'a'];
            }
            cur.end = true;
        }

        // 是否有完整的word，需要判断end
        public boolean search(String word) {
            Node cur = head;
            for (char c : word.toCharArray()) {
                // 没有false
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
                // 往下走
                cur = cur.children[c - 'a'];
            }
            return cur.end;
        }

        // 是否存在prefix路径，不需要判断end
        public boolean startsWith(String prefix) {
            Node cur = head;
            for (char c : prefix.toCharArray()) {
                // 没有false
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
                // 往下走
                cur = cur.children[c - 'a'];
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
