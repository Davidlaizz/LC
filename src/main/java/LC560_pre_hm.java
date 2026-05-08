import java.util.HashMap;
import java.util.Map;

public class LC560_pre_hm {
    // 方法1: 前缀和 + HashMap(推荐)
    //   思路：pre表示当前前缀和，若存在历史前缀和pre-k
    //         则这两者之间的子数组和为k
    //   统计：HashMap记录"前缀和 -> 出现次数"，可一次累加贡献
    //   关键：初始化hm.put(0, 1)，表示前缀和恰好等于k的情况
    //   复杂度：时间O(n)，空间O(n)
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

    // 方法2: 暴力枚举
    //   思路：固定右端点start，再从start向左扩展end
    //   过程：sum初始为0，每次把nums[end]加入sum
    //         此时sum表示区间[end..start]的元素和
    //   判定：每扩展一步就判断sum == k，成立则count+1
    //   说明：向左扩展可复用sum，避免每次重新累加整段区间
    //   特点：实现直观，但仍会枚举所有区间
    //   复杂度：时间O(n²)，空间O(1)
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

}
