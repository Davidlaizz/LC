import java.util.Stack;

public class LC739_msta {
    // 方法: 单调栈(递减)
    //   目标：找到每个位置右边第一个更大元素的距离
    //   思路：维护递减栈，栈顶遇到更大元素时弹出并计算距离
    //   过程：遍历数组，若当前 > 栈顶，则栈顶找到了答案
    //         弹出栈顶，res[index] = i - index
    //   关键：栈存下标，栈顶到栈底递减
    //   终止：遍历结束，栈中剩余元素答案为0（默认值）
    //   复杂度：时间O(n)，空间O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 栈顶元素小于等于当前元素
            while (!s.isEmpty() && temperatures[i] > temperatures[s.peek()]) {
                int index = s.pop();
                res[index] = i - index;
            }
            s.push(i);
        }
        // // 处理后面没有更高气温的情况
        // while(!s.isEmpty()){
        //     int index = s.pop();
        //     res[index] = 0;
        // }
        return res;
    }
}
