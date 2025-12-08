import java.util.HashSet;
import java.util.Set;

public class LC3_sw {
    //模板
    //外层循环扩展右边界，内层循环扩展左边界
//        for (int l = 0, r = 0 ; r < n ; r++) {
//        //当前考虑的元素
//        while (l <= r && check()) {//区间[left,right]不符合题意
//            //扩展左边界
//        }
//        //区间[left,right]符合题意，统计相关信息
//    }
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> hs = new HashSet<>();
        int res = 0;
        //外层循环扩展右边界，内层循环扩展左边界
        for (int l = 0, r = 0 ; r < n ; r++) {
            //当前考虑的元素
            char c = s.charAt(r);
            while (l <= r && hs.contains(c)) {//区间[left,right]不符合题意
                //扩展左边界,删除之前的c以及c之前的元素
                hs.remove(s.charAt(l));
                l++;
            }
            //区间[left,right]符合题意，统计相关信息
            res = Math.max(res, r - l + 1);
            hs.add(c);
        }
        return res;
    }
}
