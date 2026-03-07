import java.util.Stack;

public class LC20_sta {
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
