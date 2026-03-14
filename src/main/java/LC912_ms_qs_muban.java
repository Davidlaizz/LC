import java.util.Arrays;

public class LC912_ms_qs_muban {
    public int[] sortArray(int[] nums) {
        // mergeSort(nums, 0, nums.length - 1);
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // [l, r]
    public void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return; // l == r时就是单独的一个元素
        }
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, r, mid);
    }

    public void merge(int[] nums, int l, int r, int mid) {
        int[] temp = new int[r - l + 1];
        int k = 0;
        int i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        // 多的部分直接接上
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= r) {
            temp[k++] = nums[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            nums[l + m] = temp[m];
        }
    }

    // [l, r]
    public void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return; // l == r时就是单独的一个元素
        }
        // int p = partition(nums, l, r);
        // quickSort(nums, l, p - 1);
        // quickSort(nums, p + 1, r);

        int p = partition2(nums, l, r);
        quickSort(nums, l, p);
        quickSort(nums, p + 1, r);
    }

    // lomuto
    // 返回枢轴的下标，枢轴修改后的位置就是元素的最终位置
    // 对比次数过多：[1,2,3,...,n]这种情况swap n次
    public int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l, j = l;
        // 快慢指针
        // i表示有多少个比pivot小的数
        // j用来遍历，找比pivot小的数
        // [0, r - 1]
        while (j < r) {
            if (nums[j] < pivot) { // 对比次数过多，比如第一次如果满足条件，会做无效swap
                swap(nums, i, j);
                i++;
            }
            // 找不找到j都在遍历
            j++;
        }
        // 遍历完了之后，将pivot放在中间
        swap(nums, i, r);
        return i;
    }

    // hoare
    // 枢轴位置不是该元素最终的位置
    // 效率高，对比次数少
    // 分区[l, j] [j + 1, r] // i可能==j 也可能i == j + 1
    public int partition2(int[] nums, int l, int r) {
        int mid = l + (r - l) / 2;
        int pivot = nums[mid];
        // 左右指针
        int i = l, j = r;
        // i指向比pivot >= 的元素
        // j指向比pivot <= 的元素
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
//        System.out.println(Arrays.toString(nums));
        return j;
    }

    /*
     循环不变量（始终保持）：

     [L ... less]         < pivot
     [less+1 ... index-1] = pivot
     [index ... more-1]   未处理
     [more ... R]         > pivot
    */
    /*
        划分结束后数组结构：

        [L ... less]        < pivot
        [less+1 ... more-1] = pivot
        [more ... R]        > pivot
    */
    // 荷兰国旗 三路快排
    private void partition3(int[] nums, int L, int R) {
        // 递归终止条件：区间只有0个或1个元素
        if (L >= R) return;
        // 选择基准值 pivot，也可以是随机值
        int pivot = nums[L];
        // index: 当前扫描的位置
        int index = L;
        // less: 小于 pivot 区域的右边界
        // 初始为 L-1 表示 <pivot 区域还不存在
        int less = L - 1;
        // more: 大于 pivot 区域的左边界
        // 初始为 R+1 表示 >pivot 区域还不存在
        int more = R + 1;

        while (index < more) {
            // 当前元素等于 pivot
            // 扩大 =pivot 区域
            if (nums[index] == pivot) {
                index++;
            }
            // 交换到 <pivot 区域
            else if (nums[index] < pivot) {
                swap(nums, index++, ++less);
            }
            // 交换到 >pivot 区域
            else {
                swap(nums, index, --more);
                // 注意这里 index 不++，因为换回来的元素还没比较过
            }
        }
        // 递归处理小于 pivot 的部分
        partition3(nums, L, less);
        // 递归处理大于 pivot 的部分
        partition3(nums, more, R);
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
