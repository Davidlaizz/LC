public class LC189_gr {
    // 方法1: 额外数组
    //   思路：把nums[i]放到新数组下标(i + k) % n，再拷贝回原数组
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法2: 环状替换(循环置换)
    //   思路：沿着(i + k) % n形成的环依次置换元素
    //   关键：可能有多个环，通常用count或gcd(n, k)控制环数
    //   复杂度：时间O(n)，空间O(1)
    //
    // 方法3: 三次翻转
    //   思路：先翻转整体，再翻转前k段和后n-k段
    //   复杂度：时间O(n)，空间O(1)

    // 对应方法1：额外数组
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

    // 对应方法3：三次翻转
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    // 反转下标区间[l, r]
    public void reverse(int[] nums, int l, int r){
        int temp;
        while(l < r){
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}
