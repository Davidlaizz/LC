import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class LC347_heap {
    // 方法: 小根堆
    //   目标：返回数组中出现频率前k高的元素
    //   思路：先用HashMap统计频率{num, cnt}，再用大小为k的小根堆维护前k高频元素
    //   过程：
    //     1. HashMap统计每个元素出现次数
    //     2. 遍历map，维护size=k的小根堆（按频率排序）
    //     3. 若堆满且当前频率 > 堆顶，弹出堆顶并入堆
    //     4. 最后堆中元素即为前k高频
    //   关键：小根堆堆顶是当前k个中最小的，保证替换的是最小频率
    //   复杂度：时间O(n log k)，空间O(n)
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
