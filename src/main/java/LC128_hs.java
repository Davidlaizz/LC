import java.util.HashSet;
import java.util.Set;

public class LC128_hs {
    // 方法: HashSet
    //   思路：将所有元素加入Set，遍历判断每个元素是否为连续序列开头
    //         若num-1不在Set中，则num是开头，向后查找连续元素
    //   关键：只从序列开头开始计数，避免重复计算
    //   复杂度：时间O(n)，空间O(n)
    //          每个元素最多被访问两次
    public int longestConsecutive(int[] nums) {
        Set<Integer> hs = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            hs.add(nums[i]);
        }
        int res = 0;
        int cnt = 0;
        for(int num : hs){
            //判断是否为连续序列的开头
            if(hs.contains(num - 1)){
                continue;
            }else{
                //是开头
                cnt++;
                int cur = num + 1;
                //还有连续元素
                while(hs.contains(cur)){
                    cnt++;
                    cur++;
                }
                res = Math.max(cnt, res);
                cnt = 0;
            }
        }
        return res;
    }
}
