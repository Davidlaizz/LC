import java.util.HashMap;
import java.util.Map;

public class LC1_hm {
    // 方法1: 哈希表
    //   思路：先遍历构建map(值->索引)，再遍历查找target-nums[i]是否存在
    //   优化：两步可合并为一次遍历
    //         遍历时先查target-nums[i]是否已在map中，再将nums[i]加入map
    //   复杂度：时间O(n)，空间O(n)
    // 方法2: 暴力
    //   思路：双重循环枚举所有两两组合
    //   复杂度：时间O(n²)，空间O(1)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> ht = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(ht.containsKey(target - nums[i])){
                return new int[]{i, ht.get(target - nums[i])};
            }else{
                ht.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        int[] res = new int[2];
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(nums[i] + nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }
}
