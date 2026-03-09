import java.util.PriorityQueue;
import java.util.Collections;

public class MaxHeap {
    public static void main(String[] args) {
        // 创建大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // --- 增 ---
        maxHeap.offer(15);
        maxHeap.offer(8);
        maxHeap.offer(30);
        maxHeap.offer(1);
        
        System.out.println("当前堆顶 (最大值): " + maxHeap.peek()); // peek() 只看不删，输出 30

        // --- 删 (弹出) ---
        int maxVal = maxHeap.poll();
        System.out.println("弹出的元素: " + maxVal); // 输出 30
        System.out.println("弹出后的新堆顶: " + maxHeap.peek()); // 输出 15

        // --- 删 (指定元素) ---
        maxHeap.remove(8); // 把 8 移除了
        System.out.println("移除 8 之后，堆里还有: " + maxHeap); // 打印底层数组，可能不按绝对顺序，但满足堆性质
    }
}