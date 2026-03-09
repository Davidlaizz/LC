import java.util.Arrays;

// 小根堆
public class MinHeap {
    int[] arr;
    public MinHeap(int[] arr) {
        this.arr = arr;
    }
    public void heapSort() {
        buildHeap();
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[0] + " ");
            pop();
        }
    }
    public void buildHeap() {
        // 找最后的非叶子节点
        int lastNonLeafIndex = arr.length / 2 - 1;
        // 使用siftDown复杂度On
        for (int i = lastNonLeafIndex; i >= 0; i--) {
            siftDown(i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 10, 9, 22, 31, 15, 40, 25, 91};
        MinHeap h = new MinHeap(arr);
        System.out.println("min heap: " + Arrays.toString(h.arr));

        int valToRemove = h.pop();
        System.out.println("valToRemove: " + valToRemove);
        System.out.println("after pop: " + Arrays.toString(h.arr));

        h.insert(2);
        System.out.println("after insert val 2: " + Arrays.toString(h.arr));

        h.heapSort();
    }

    // 上滤
    public void siftUp(int index) {
        int parentIndex = (index - 1) / 2;
        int swapIndex = index;
        // 小顶堆：比父节点小才会上升
        if (swapIndex < arr.length && arr[swapIndex] < arr[parentIndex]) {
            swapIndex = parentIndex;
        }
        if (swapIndex != index) {
            swap(arr, swapIndex, index);
            // 上升之后，可能还能上升
            siftUp(swapIndex);
        }
    }

    // 下滤
    public void siftDown(int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int swapIndex = index;
        // 比子节点大就下沉
        if (leftIndex < arr.length && arr[swapIndex] > arr[leftIndex]) {
            swapIndex = leftIndex;
        }
        // swapIndex最后对应最大的节点
        if (rightIndex < arr.length && arr[swapIndex] > arr[rightIndex]) {
            swapIndex = rightIndex;
        }
        if (swapIndex != index) {
            swap(arr, swapIndex, index);
            // 可能还能继续下沉
            siftDown(swapIndex);
        }
    }

    // 插入 pop都会影响arr数组大小，通常情况会维护定长的较大的capacity的数组, size表示当前大小， 动态扩容
    public void insert(int val) {
        // 扩容 + 1 这样写纯为了练手演示
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = val;
        siftUp(arr.length - 1);
    }
    public int pop() {
        int top = arr[0];
        swap(arr, 0, arr.length - 1);
        arr = Arrays.copyOf(arr, arr.length - 1);
        siftDown(0);
        return top;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
