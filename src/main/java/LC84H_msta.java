import java.util.Arrays;
import java.util.Stack;

public class LC84H_msta {
    // 接雨水的变种题目
    // 用单调栈（栈顶最大）的方法优化了left、right数组的确定
    // 暴力时间On*n 空间O1
    // 优化后:  On   On
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 出栈确定 栈顶元素 的右边界
            while (!s.isEmpty() && heights[s.peek()] > heights[i]) {
                int index = s.pop();
                right[index] = i;
            }
            // 入栈确定当前 入栈元素 的左边界
            if (!s.isEmpty()) {
                // 不空就说明有比入栈元素小的元素在栈里，确定入栈元素左边界
                left[i] = s.peek();
            }
            s.push(i);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cur = (right[i] - left[i] - 1) * heights[i];
            max = Math.max(max, cur);
        }
        return max;
    }
}
