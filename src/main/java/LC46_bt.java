import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LC46_bt {
    // 方法: 回溯(递归+撤销)
    //   目标：求无重复元素数组的所有全排列
    //   思路：用row记录当前路径，used[]标记已选元素
    //   过程：每层遍历所有元素，跳过已选的，选一个加入row，递归后撤销
    //   终止：row.size() == nums.length 时收集一个排列
    //   去重：used[i]为true表示该位置元素已在当前路径中
    //   复杂度：时间O(n*n!)，空间O(n)
    //          共n!个排列，每个排列构建需要O(n)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            str = str.substring(1, str.length() - 1);
            String[] arr = str.split(",");
            int[] data = new int[arr.length];
            for(int i = 0; i < data.length; i++){
                data[i] = Integer.parseInt(arr[i]);
            }
            LC46_bt slove = new LC46_bt();
            List<List<Integer>> r = slove.permute(data);
            System.out.println(r.toString());
        }

    }

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> row = new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backTracking(nums);
        return res;
    }
    public void backTracking(int[] nums){
        if(row.size() == nums.length){
            res.add(new ArrayList<>(row));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]){
                continue; //重复则跳过 也可以使用row.contains(nums[i])
            }
            used[i] = true;
            row.add(nums[i]);
            backTracking(nums);
            row.removeLast();
//            row.remove(rowg.size() - 1);
            used[i] = false;
        }
    }
}
