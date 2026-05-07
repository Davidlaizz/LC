import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC438_sw {
    // 方法1: 固定长度滑动窗口 + 双计数数组
    //   思路：维护长度为|p|的窗口，分别统计窗口与p的26字母频次
    //   判定：每次滑动后比较sCount与pCount，若相同则记录起点
    //   特点：实现直观，判断成本为O(26)
    //   复杂度：时间O(ns * 26)，空间O(1)
    // 方法2: 优化滑动窗口 + 差分数组
    //   思路：count记录窗口与p的频次差，differ记录不相等字母个数
    //   判定：每次滑动只更新进出窗口两个字符对differ的影响
    //         当differ == 0时，当前窗口是异位词
    //   特点：判断成本降为O(1)，但实现更复杂
    //   复杂度：时间O(ns + 26)，空间O(1)

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int ns = s.length();
        int np = p.length();
        if(np > ns)
            return res;

        int sCount[] = new int[26];
        int pCount[] = new int[26];

        // 初始化pCount
        for(int i = 0; i < np; i++){
            pCount[p.charAt(i) - 'a']++;
        }

        // 初始化首个窗口sCount
        for(int i = 0; i < np; i++){
            sCount[s.charAt(i) - 'a']++;
        }

        if(Arrays.equals(pCount, sCount)){
            res.add(0);
        }

        for(int l = 0, r = np; r < ns; l++, r++){
            sCount[s.charAt(r) - 'a']++;
            sCount[s.charAt(l) - 'a']--;
            if(Arrays.equals(pCount, sCount)){
                res.add(l + 1);
            }
        }
        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] count = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }

        int differ = 0;
        for (int j = 0; j < 26; ++j) {
            if (count[j] != 0) {
                ++differ;
            }
        }

        if (differ == 0) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            if (count[s.charAt(i) - 'a'] == 1) {
                --differ;
            } else if (count[s.charAt(i) - 'a'] == 0) {
                ++differ;
            }
            --count[s.charAt(i) - 'a'];

            if (count[s.charAt(i + pLen) - 'a'] == -1) {
                --differ;
            } else if (count[s.charAt(i + pLen) - 'a'] == 0) {
                ++differ;
            }
            ++count[s.charAt(i + pLen) - 'a'];

            if (differ == 0) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}
