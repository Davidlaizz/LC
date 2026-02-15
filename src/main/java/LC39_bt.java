import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC39_bt {
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
