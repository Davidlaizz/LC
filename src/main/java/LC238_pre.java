public class LC238_pre {
    // 方法1: 前缀积 + 后缀积数组
    //   思路：prefix[i]存i左侧乘积，postfix[i]存i右侧乘积
    //   结果：res[i] = prefix[i] * postfix[i]
    //   复杂度：时间O(n)，空间O(n)
    //
    // 方法2: 空间优化(推荐)
    //   思路：res先存前缀积，再从右向左用postfix累乘并合并到res
    //   关键：不再单独开postfix数组，只用一个变量postfix
    //   复杂度：时间O(n)，空间O(1)
    //          其中返回数组res不计入额外空间
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int prefix[] = new int[n]; //i(不包括i)之前的乘积
        int postfix[] = new int[n]; //i(不包括i)之后的乘积
        int res[] = new int[n];
        prefix[0] = 1;
        postfix[n - 1] = 1;
        for(int i = 1; i < n; i++){
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        for(int i = n - 2; i >= 0; i--){
            postfix[i] = postfix[i + 1] * nums[i + 1];
        }

        for(int i = 0; i < n; i++){
            res[i] = prefix[i] * postfix[i];
        }
        return res;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int res[] = new int[n];
        res[0] = 1;
        //res先当作前缀积
        for(int i = 1; i < n; i++){
            res[i] = res[i - 1] * nums[i - 1];
        }
        //后缀积直接计算
        int postfix = 1;
        for(int i = n - 2; i >= 0; i--){
            postfix = postfix * nums[i + 1];
            res[i] *= postfix;
        }

        return res;
    }
}
