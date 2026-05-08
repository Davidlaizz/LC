import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC39_bt {
    // 方法: 回溯(递归+撤销)
    //   目标：求无重复元素数组中和为target的所有组合（每个元素可重复选）
    //   思路：用curSum记录当前路径和，startIndex控制起点避免重复组合
    //   关键：递归时传i而不是i+1，表示当前元素可以重复选取
    //   剪枝：先排序，若curSum + candidates[i] > target则break
    //         后面的元素更大，不可能满足条件
    //   终止：curSum == target 时收集结果
    //   复杂度：时间O(n^target/min)，空间O(target/min)
    //          取决于组合数量和路径长度
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> row = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return res;
    }
    public void backTracking(int[] candidates, int target, int startIndex, int curSum){
        if(curSum == target){
            res.add(new ArrayList<>(row));
            return;
        }
        // if(curSum > target){
        //     return;
        // } 将终止条件优化成进入回溯前的检查，剪枝
        for(int i = startIndex; i < candidates.length; i++){
            // 剪枝
            if(curSum + candidates[i]> target)
                break; // 排序candidates后,后面都是更大的情况，省略
            row.add(candidates[i]);
            backTracking(candidates, target, i, curSum + candidates[i]);
            row.removeLast();
        }
    }
}
