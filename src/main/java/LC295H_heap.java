import java.util.PriorityQueue;

public class LC295H_heap {
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
