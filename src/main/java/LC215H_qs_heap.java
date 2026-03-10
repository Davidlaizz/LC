import java.util.Arrays;

public class LC215H_qs_heap {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    public int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int n = nums.length;
        // k表示第几大， n - k表示第k大的具体下标
        int p = hoarePartition(nums, l, r);
        if (n - k <= p) {
            return quickSelect(nums, l, p, k);
        } else {
            return quickSelect(nums, p + 1, r, k);
        }
    }
    // [l, j] [j + 1, r] pivot可能在两边的任意一边
    public int hoarePartition(int[] nums, int l, int r) {
        int pivot = nums[l + (r - l) / 2];
        int i = l, j = r;
        while (i <= j) {
            while (nums[i] < pivot) {
                i++;
            }
            while (nums[j] > pivot) {
                j--;
            }
            if (i == j) {
                break;
            }
            if (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        return j;
    }

    // [l, r] 这个算法对重复元素或者顺序元素会退化到On^2
    // public int lomutoPartition(int[] nums, int l, int r) {
    //     int pivot = nums[r];
    //     System.out.println("pivot " + pivot);
    //     int i = l, j = l;
    //     while (j < r) {
    //         if (nums[j] < pivot) {
    //             swap(nums, i, j);
    //             // System.out.println(Arrays.toString(nums));
    //             i++;
    //         }
    //         j++;
    //     }
    //     // System.out.println(Arrays.toString(nums));
    //     swap(nums, i, r);
    //     return i; // 枢轴的最后位置
    // }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public int findKthLargest2(int[] nums, int k) {
        int[] arr = Arrays.copyOf(nums, k);
        MinHeap h = new MinHeap(arr, k);
        h.buildHeap();
        for (int i = k; i < nums.length; i++) {
            h.mainTain(nums[i]);
        }
        return h.peek();
    }
    // 维护一个size = k的小根堆，堆顶就是所求
    public class MinHeap {
        int size;
        int[] arr;
        public MinHeap(int[] arr, int size) {
            this.arr = arr;
            this.size = size;
        }
        public void buildHeap() {
            int lastNonLeafIndex = size/ 2 - 1;
            for (int i = lastNonLeafIndex; i >= 0; i--) {
                siftDown(i);
            }
        }
        public int peek() {
            return arr[0];
        }
        public void mainTain(int val) {
            if (val > arr[0]) {
                arr[0] = val;
                siftDown(0);
            }
        }
        public void siftDown(int index) {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            int swapIndex = index;
            if (leftIndex < size && arr[swapIndex] > arr[leftIndex]) {
                swapIndex = leftIndex;
            }
            if (rightIndex < size && arr[swapIndex] > arr[rightIndex]) {
                swapIndex = rightIndex;
            }
            if (swapIndex != index) {
                swap(arr, swapIndex, index);
                siftDown(swapIndex);
            }
        }
        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
