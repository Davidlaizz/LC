import java.util.ArrayList;
import java.util.List;

public class LC118_dp {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dp = new ArrayList<List<Integer>>();
        for(int i = 0; i < numRows; i++){ // 遍历行
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j <= i; j++){ // 遍历行中元素
                if(j == 0 || j == i){
                    row.add(1);
                }else{
                    row.add(dp.get(i - 1).get(j - 1) + dp.get(i - 1).get(j));
                }
            }
            dp.add(row);
        }
        return dp;
    }
}
