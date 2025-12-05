import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC15_2p {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//         List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            // 跳过重复元素
            if(i >= 1 && nums[i] == nums[i - 1]) continue;
            // 跳过全是正数的情况
            if(nums[i] > 0) continue;
            int target = -nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            while(l < r){
                int sum = nums[l] + nums[r];
                if(sum == target){
                    res.add(List.of(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    // 跳过重复元素
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                }else if(sum > target){
                    r--;
                }else{
                    l++;
                }
            }
        }
        return res;
    }


    // 回溯超时
    // List<List<Integer>> res = new ArrayList<>();
    // List<Integer> list = new ArrayList<>();
    // public List<List<Integer>> threeSum(int[] nums) {
    //     Arrays.sort(nums); // 先排序
    //     backTracking(nums, 0);
    //     return res;
    // }
    // public void backTracking(int[] nums, int startIndex){
    //     if(list.size() == 3){
    //         if(list.stream().mapToInt(Integer::intValue).sum() == 0){
    //             res.add(new ArrayList<>(list));
    //         }
    //         return;
    //     }

    //     for(int i = startIndex; i < nums.length; i++){
    //         // ⭐ 同一层去重：避免重复选相同数
    //         if(i > startIndex && nums[i] == nums[i - 1]) continue;

    //         // ⭐ 剪枝：如果当前集合已经无法达到 3 个数字则剪枝（可选）
    //         if(nums.length - i < 3 - list.size()) break;
    //         list.add(nums[i]);
    //         backTracking(nums, i + 1);
    //         list.removeLast();
    //     }
    // }


}
