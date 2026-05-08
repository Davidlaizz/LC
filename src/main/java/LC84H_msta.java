import java.util.Arrays;
import java.util.Stack;

public class LC84H_msta {
    // 方法: 单调栈(递增)
    //   目标：求柱状图中最大矩形面积
    //   思路：对每个柱子，找左右第一个比它矮的柱子，计算宽度
    //   过程：维护递增栈，栈顶遇到更矮元素时弹出并确定右边界
    //         弹出时：right[index] = i，left[index] = 栈顶(弹出后)
    //   面积计算：宽 = right[i] - left[i] - 1，高 = heights[i]
    //   边界：left初始化-1，right初始化n
    //   复杂度：时间O(n)，空间O(n)
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
