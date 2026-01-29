import java.util.HashMap;
import java.util.Map;

public class LC560_pre_hm {
    public int subarraySum(int[] nums, int k) {
        // 前缀和:出现次数
        Map<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1);
        // 由于不关心前缀和的下标,所以只需要当前前缀和,前缀和次数由hm记录
        int pre = 0, cnt = 0;
        for(int i = 0; i < nums.length; i++){
            pre += nums[i];
            int val = pre - k;
            //找index更小的前缀和
            if(hm.containsKey(val)){
                cnt += hm.get(val);
            }
            //添加现有pre
            hm.put(pre, hm.getOrDefault(pre, 0) + 1);

        }
        return cnt;
    }
}
