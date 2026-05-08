import java.util.ArrayList;
import java.util.List;

public class LC78_bt {
    // 方法: 回溯(递归+撤销)
    //   目标：求无重复元素数组的所有子集
    //   思路：每个节点都收集结果（空集也是子集）
    //   过程：用startIndex控制起点，避免重复选取同一元素
    //   关键：每层从startIndex开始遍历，递归时i+1保证只选后面的元素
    //   终止：startIndex >= nums.length时结束（其实for循环会自然结束）
    //   复杂度：时间O(n*2^n)，空间O(n)
    //          共2^n个子集，每个子集构建需要O(n)
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
