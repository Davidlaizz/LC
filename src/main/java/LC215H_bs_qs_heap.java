import java.util.Arrays;

public class LC215H_bs_qs_heap {
    // 方法1: 快速选择(Quick Select)
    //   目标：找到数组中第k大的元素
    //   思路：基于快排的分区思想，只处理目标所在的那一半
    //   过程：partition后，若n-k <= p，目标在左半边，否则在右半边
    //   复杂度：平均O(n)，最坏O(n²)；空间O(1)
    //
    //   分区方法对比：
    //   |          | Hoare                    | Lomuto                  |
    //   |----------|--------------------------|-------------------------|
    //   | 指针     | 双指针从两端向中间        | 单指针从左向右          |
    //   | pivot    | 选中间元素，避免死循环    | 选最右元素              |
    //   | 分区结果 | [l, j] [j+1, r]，pivot在两边 | [l, i-1] [i, r]，pivot在i |
    //   | 交换次数 | 少                        | 多                     |
    //   | 适用场景 | 重复元素多时推荐          | 重复元素少时推荐        |
    //   为什么Hoare交换少
    //   Lomuto：元素<pivot就交换，相等元素全留一边 → 升序时退化 O(n²)
    //   Hoare：ij分别找出符合条件的值才交换 

    // 方法2: 小根堆
    //   目标：维护前k大的元素，堆顶即为第k大
    //   思路：构建size=k的小根堆，遍历剩余元素，大于堆顶则替换
    //   过程：buildHeap构建堆，maintain维护堆性质
    //   复杂度：时间O(n log k)，空间O(k)
    public int findKthLargest(int[] nums, int k) {
        return quickSelectHoare(nums, 0, nums.length - 1, k);
        // return quickSelectLomuto(nums, 0, nums.length - 1, k);
    }
    public int quickSelectHoare(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int n = nums.length;
        // k表示第几大， n - k表示第k大的具体下标
        int p = hoarePartition(nums, l, r);
        if (n - k <= p) {
            return quickSelectHoare(nums, l, p, k);
        } else {
            return quickSelectHoare(nums, p + 1, r, k);
        }
    }
    // [l, j] [j + 1, r] pivot可能在两边的任意一边
    public int hoarePartition(int[] nums, int l, int r) {
        // 选择j作为结果，枢轴不能选择最右边（除以2向上取整也不行）
        // 比如[2,3,1,6],永远返回j=3,[0,3]永远递归下去，nums[3] = 6永远不变化就死循环
        // 同理，以i作为结果[l, i - 1] [i, r] 枢轴不能选择最左边边（除以2向下取整也不行）
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

    public int quickSelectLomuto(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int n = nums.length;
        int p = lomutoPartition(nums, l, r);
        if (p == n - k) {
            return nums[p];
        } else if (p < n - k) {
            return quickSelectLomuto(nums, p + 1, r, k);
        } else {
            return quickSelectLomuto(nums, l, p - 1, k);
        }
    }
    // [l, r] 这个算法对重复元素或者顺序元素会退化到On^2，
    //        用if (i != j) 跳过自交换
    public int lomutoPartition(int[] nums, int l, int r) {
        int pivot = nums[r];
        // System.out.println("pivot " + pivot);
        int i = l, j = l;
        while (j < r) {
            if (nums[j] < pivot) {
                if (i != j) { // 跳过自交换
                    swap(nums, i, j);
                }
                // System.out.println(Arrays.toString(nums));
                i++;
            }
            j++;
        }
        // System.out.println(Arrays.toString(nums));
        if (i != r) {
            swap(nums, i, r);
        }
        return i; // 枢轴的最后位置
    }

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
