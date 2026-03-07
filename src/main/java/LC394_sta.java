import java.util.Stack;

public class LC394_sta {
    public String decodeString2(String s) {
        Stack<Integer> countNum = new Stack<>();
        Stack<String> countStr = new Stack<>();
        int k = 0;
        String cur = "";
        for (char c : s.toCharArray()) { //入
            if (c >= '0' && c <= '9') {
                k = 10 * k + c - '0';
            } else if (c == '[') {
                countNum.push(k); //将后面要用到的次数入栈
                countStr.push(cur.toString()); //将前面已经处理的str入栈
                k = 0;
                cur = "";
            } else if (c ==']') { // 出栈
                // 构建字符串，先构建里面的
                int times = countNum.pop();
                String pre = countStr.pop(); // 出栈之前处理好的左边的字符串
                while (times-- > 0) {
                    pre += cur;
                }
                cur = pre;
            } else {
                cur += c;
            }
        }
        return cur;
    }


    // 用StringBuilder 或者 StringBuffer（线程安全 更慢）优化普通的String
    // Java 9 使用 invokedynamic 进一步优化 动态使用优化策略
    public String decodeString(String s) {
        Stack<Integer> countNum = new Stack<>();
        Stack<String> countStr = new Stack<>();
        int k = 0;
        // String cur = "";
        StringBuilder cur = new StringBuilder();
        for (char c : s.toCharArray()) { //入
            if (c >= '0' && c <= '9') {
                k = 10 * k + c - '0';
            } else if (c == '[') {
                countNum.push(k); //将后面要用到的次数入栈
                countStr.push(cur.toString()); //将前面已经处理的str入栈
                k = 0;
                // cur = "";
                cur.setLength(0);
                // cur.delete(0, cur.length());
            } else if (c ==']') { // 出栈
                // 构建字符串，先构建里面的
                int times = countNum.pop();
                String pre = countStr.pop(); // 出栈之前处理好的左边的字符串
                StringBuilder sb = new StringBuilder();
                sb.append(pre);
                while (times-- > 0) {
                    sb.append(cur);
                }
                cur = sb;
                // System.out.println(cur);
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }
}
