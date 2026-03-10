import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class LC347_heap {
    public int[] topKFrequent(int[] nums, int k) {
        // hm用来计数，收集准备好数据
        Map<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        // 用数组比hm存储到pq要快, {num, cnt}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]); //默认就是小顶堆可不写
        // 遍历整理好的num，也就是hm的key
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int[] item = new int[]{entry.getKey(), entry.getValue()};
            if (minHeap.size() < k) {
                minHeap.offer(item);
            } else {
                // 对比小顶堆的根节点，更小就交换
                if (entry.getValue() > minHeap.peek()[1]) {
                    minHeap.poll();
                    minHeap.offer(item);
                }
            }
        }

        int[] res = new int[k];
        for (int j = 0; j < k; j++) {
            int[] top = minHeap.poll();
            res[j] = top[0];
        }
        return res;
    }

}
