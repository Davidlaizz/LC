public class LC189_gr {
    //暴力解法 空间on
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int res[] = new int[n];
        k %= n;
        for(int i = 0; i < n; i++){
            res[(i + k) % n] = nums[i];
        }
        for(int i = 0; i < n; i++){
            nums[i] = res[i];
        }
    }

    //两级反转：反转整个数组，再根据k切分数组后反转
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    //下标[startIndex, endIndex]
    public void reverse(int[] nums, int l, int r){
        int temp;
        while(l < r){ //等于的情况不用考虑
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}
