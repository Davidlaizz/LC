public class LC283_2p {
    // 方法: 双指针
    //   思路：i指向当前应放非零元素的位置，j向后扫描寻找非零元素
    //   过程：当nums[j]非零时，将其放到i位置，并在j位置补0，然后i右移
    //   结果：非零元素相对顺序不变，所有0被移动到数组末尾
    //   复杂度：时间O(n)，空间O(1)
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
