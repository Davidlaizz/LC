import java.util.HashSet;
import java.util.Set;

public class LC41H_hs_gr {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> hs = new HashSet<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            hs.add(nums[i]);
        }
        //最小正整数的范围是[1, n + 1]
        for(int i = 1; i <= n + 1; i++){
            if(hs.contains(i)) continue;
            else return i;
        }
        return 12312412; //不重要
    }

    public int firstMissingPositive2(int[] nums) {
        //原地交换 空间o1
        //规定nums中,index为i时nums[i] == i + 1
        int n = nums.length;
        for(int i = 0; i < n; i++){
            //交换后可能还要交换,所以是while
            //i + 1 != nums[i]不等价nums[nums[i] - 1] != nums[i],英文nums[nums[i] - 1] != nums[i]能保证交换的数时不重复的,如果重复会死锁
            while(i + 1 != nums[i] && nums[i] - 1 >= 0 && nums[i] - 1 < n && nums[nums[i] - 1] != nums[i]){
                //挪到正确位置
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
                // i--;

            }
        }
        // for(int i : nums){
        //     System.out.println(i);
        // }
        for(int i = 0; i < n; i++){
            if(i + 1 == nums[i]){
                continue;
            }else{
                return i + 1;
            }
        }
        return n + 1;
    }
}
