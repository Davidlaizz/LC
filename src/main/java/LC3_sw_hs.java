import java.util.HashSet;
import java.util.Set;

public class LC3_sw_hs {
    // 方法: 滑动窗口 + HashSet
    //   目标：维护一个无重复字符的窗口，求最大窗口长度
    //   模板：右指针r负责扩张窗口，左指针l在冲突时收缩窗口
    //   过程：若s[r]已在窗口中，持续移除s[l]并右移l，直到窗口恢复合法
    //   统计：每次窗口合法后，用r-l+1更新答案
    //   复杂度：时间O(n)，空间O(k)
    //          k为字符集大小，最坏可视为O(n)

    // 滑动窗口（双指针）模板
    // 外层循环扩展右边界，内层循环扩展左边界
//    for (int l = 0, r = 0 ; r < n ; r++) {
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
