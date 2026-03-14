public class LC75_qs {
    // 三路递归快排
    public void sortColors2(int[] nums) {
        int pivot = 1;
        int l = 0;
        int r = nums.length - 1;
        int less = l  - 1;
        int more = r + 1;
        int i = 0;
        while (i < more) {
            if (nums[i] == pivot) {
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, --more);
                // 此时i不变,还得检查从后面换过来的值
            } else {
                swap(nums, i++, ++less);
            }
        }
        // [less+1 ... more-1] = pivot
    }

    // 第一印象，也是三路快排
    public void sortColors(int[] nums) {
        int zeroIndex = 0;
        int twoIndex = nums.length - 1;
        // 思路：把0往左边丢，把2往右边丢
        while (zeroIndex < twoIndex && nums[zeroIndex] == 0) {
            zeroIndex++;
        }
        while (zeroIndex < twoIndex && nums[twoIndex] == 2) {
            twoIndex--;
        }
        // System.out.println(zeroIndex);
        // System.out.println(twoIndex);
        for (int i = zeroIndex; i <= twoIndex; i++) {
            // System.out.println(Arrays.toString(nums));
            if (nums[i] == 0) {
                swap(nums, i, zeroIndex);
                zeroIndex++;
                // System.out.println("i " + i);
                // System.out.println(zeroIndex);
            } else if (nums[i] == 1) {
                continue;
            } else {
                swap(nums, i, twoIndex);
                twoIndex--;
                // if (nums[i] != 1) // 重点: 如果此时和2交换,也应该判断被换回来的元素 如[1,2,0]
                i--;
                // System.out.println(twoIndex);
            }
        }
    }
    public void swap (int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
