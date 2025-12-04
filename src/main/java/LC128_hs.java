import java.util.HashSet;
import java.util.Set;

public class LC128_hs {
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
