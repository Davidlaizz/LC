import java.util.ArrayList;
import java.util.List;

public class LC763_gr {
    // 方法: 贪心
    //   目标：将字符串划分成尽可能多的片段，每个字母最多出现在一个片段中
    //   思路：记录每个字母最后出现的位置，遍历时扩展右边界
    //   过程：
    //     1. 第一遍遍历：记录每个字母的最后位置
    //     2. 第二遍遍历：r = max(r, 最后位置)，i == r时划分一个片段
    //   关键：i == r 表示当前片段所有字母都不会再出现
    //   复杂度：时间O(n)，空间O(1)（字母表大小固定）
    public List<Integer> partitionLabels(String s) {
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i; // 记录最后出现的位置
        }

        List<Integer> res = new ArrayList<>();
        int l = -1, r = 0;
        for (int i = 0; i < s.length(); i++) {
            r = Math.max(r, hash[s.charAt(i) - 'a']);
            if (i == r) {
                res.add(r - l);
                l = i;
            }
        }
        return res;
    }
}
