import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC15_2p {
    // 方法: 排序 + 双指针
    //   思路：先排序，固定第一个数nums[i]，
    //         在右侧区间用双指针查找两数和为-target
    //   去重：i、l、r三个位置都要跳过重复值，避免重复三元组
    //   剪枝：排序后若nums[i] > 0，后续三数和不可能为0，可提前结束
    //   复杂度：时间O(n²)，空间O(1)
    //          若计入排序栈空间，额外为O(logn)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        // 固定第一个数nums[i]，在右侧区间用双指针查找两数和为-target
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
//                    res.add(List.of(nums[i], nums[l], nums[r]));
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
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
