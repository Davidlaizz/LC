import java.util.Stack;

public class LC20_sta {
    // 方法: 栈
    //   目标：判断括号字符串是否有效（左右匹配且正确嵌套）
    //   思路：遇左括号入栈，遇右括号检查栈顶是否匹配
    //   过程：遍历字符串，左括号入栈，右括号与栈顶匹配则出栈
    //   边界：字符串长度为奇数直接返回false
    //         右括号时栈为空，说明没有匹配的左括号
    //   终止：遍历结束，栈空则有效
    //   复杂度：时间O(n)，空间O(n)
    public boolean isValid(String s) {
        if (s.length() % 2 == 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.size() == 0) { // 容易忽略！！
                return false;
            } else if (c == ')' && stack.peek() == '(') {
                stack.pop();
                continue;
            } else if (c == ']' && stack.peek() == '[') {
                stack.pop();
                continue;
            } else if (c == '}' && stack.peek() == '{') {
                stack.pop();
                continue;
            } else
                return false;
        }
        return stack.isEmpty(); //stack不空表示左括号没有对应的右括号
    }

}
