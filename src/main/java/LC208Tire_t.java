public class LC208Tire_t {
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
