import java.util.ArrayList;
import java.util.List;

public class LC78_bt {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> row = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backTracking(nums, 0);
        return res;
    }
    public void backTracking(int[] nums, int startIndex){
        res.add(new ArrayList<>(row));
        if(startIndex >= nums.length){ //其实可以不需要加终止条件，因为startIndex >= nums.size()，本层for循环本来也结束了。
            return;
        }

        for(int i = startIndex; i < nums.length; i++){
            row.add(nums[i]);
            backTracking(nums, i + 1);
            row.remove(row.size() - 1);
        }
    }
}
