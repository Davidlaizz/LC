public class LC283_2p {
    public void moveZeroes(int[] nums) {
        int i = 0;
        //i找第一个0
        while(i < nums.length && nums[i] != 0){
            i++;
        }
        int j;
        for(j = i + 1; j < nums.length; j++){
            if(nums[j] != 0){
                // 将num[j]与0交换
                nums[i] = nums[j];
                nums[j] = 0;
                i++;
            }
        }
    }
}
