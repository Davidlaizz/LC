import java.util.Stack;

public class LC155_sta {
    Stack<Integer> stack;
    Stack<Integer> min_stack;

    public LC155_sta() {
        stack = new Stack<>();
        min_stack = new Stack<>();
        min_stack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        min_stack.push(val < min_stack.peek() ? val : min_stack.peek());
    }

    public void pop() {
        stack.pop();
        min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}
