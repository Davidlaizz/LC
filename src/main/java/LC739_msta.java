import java.util.Stack;

public class LC739_msta {
    // 单调栈：找到第一个大于cur的值 时间空间都是On
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
