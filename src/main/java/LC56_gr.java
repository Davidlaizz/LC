import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC56_gr {
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
