import java.util.Stack;

public class LC155_sta {
    // 方法: 双栈
    //   目标：实现能O(1)获取最小值的栈
    //   思路：用辅助栈min_stack同步记录每个位置的最小值
    //   过程：push时min_stack压入min(val, min_stack.peek())
    //         pop时两个栈同步弹出
    //   关键：min_stack栈顶始终是当前栈中的最小值
    //   复杂度：所有操作时间O(1)，空间O(n)
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
