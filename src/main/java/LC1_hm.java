import java.util.HashMap;
import java.util.Map;

public class LC1_hm {
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
