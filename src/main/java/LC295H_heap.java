import java.util.PriorityQueue;

public class LC295H_heap {
    // 方法: 双堆(大顶堆+小顶堆)
    //   目标：实现数据流的中位数查找，支持动态添加和查询
    //   思路：用两个堆分割数据，大顶堆存左半部分(≤中位数)，小顶堆存右半部分(>中位数)
    //   过程：
    //     addNum：根据num与maxHeap堆顶比较，决定放入哪个堆
    //             放入后检查堆大小差，超过1则调整平衡
    //             调整：堆顶元素 从一边移到另一边。
    //     findMedian：两堆大小相等取平均，否则取maxHeap堆顶
    //   不变量：maxHeap.size() == minHeap.size() 或 maxHeap.size() == minHeap.size() + 1
    //   复杂度：addNum O(log n)，findMedian O(1)

    class MedianFinder {
        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;
        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
        }
        // 1, 2, 3, 4, 5
        // 大顶堆：<= 中位数 3, 2, 1 (大顶堆最多比小顶堆多)
        // 小顶堆：>  中位数 4, 5
        public void addNum(int num) {
            if (maxHeap.size() == 0 || num <= maxHeap.peek()) {
                maxHeap.offer(num);
                if (maxHeap.size() - minHeap.size() > 1) {
                    //调整堆
                    int val = maxHeap.poll();
                    minHeap.offer(val);
                }
            } else {
                minHeap.offer(num);
                if (minHeap.size() > maxHeap.size()) {
                    int val = minHeap.poll();
                    maxHeap.offer(val);
                }
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                int l = maxHeap.peek();
                int r = minHeap.peek();
                return r + (l - r) / 2.0;
            } else
                return (double)maxHeap.peek();
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
