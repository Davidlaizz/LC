public class LC238_pre {
    //前缀积和后缀积
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

    //优化成原地空间o1(结果数组不算)
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
