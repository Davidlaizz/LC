import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC56_gr {
    // 方法: 排序 + 贪心合并区间
    //   思路：先按区间左端点升序排序，再线性扫描并维护当前合并区间[l, r]
    //   合并：若下一区间左端点 <= r，说明重叠，更新r为两者右端点最大值
    //   切换：若下一区间左端点 > r，说明不重叠，先保存当前区间，再开始新区间
    //   收尾：循环结束后，别忘了把最后一个当前区间加入结果
    //   复杂度：时间O(nlogn)，空间O(n)
    //          排序O(nlogn)，结果列表最坏存n个区间
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);
        List<int[]> list = new ArrayList<>();
        int l = intervals[0][0];
        int r = intervals[0][1];
        for(int[] interval : intervals){
            //排序了,只用扩展右边
            //重叠 扩展r
            if(l <= interval[0] && interval[0] <= r){
                r = Math.max(r, interval[1]);
            }
            //不重叠 添加旧的 记录新的
            else if(r < interval[0]){
                list.add(new int[]{l, r});
                l = interval[0];
                r = interval[1];
            }
        }
        //加入最后的区间
        list.add(new int[]{l, r});
        return list.toArray(new int[list.size()][]);
    }
}
