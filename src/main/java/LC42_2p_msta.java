import java.util.Deque;
import java.util.LinkedList;

public class LC42_2p_msta {
    // 方法1: 双指针 竖着算
    //   思路：维护左右最大高度lmax/rmax，较小一侧可确定当前接水量
    //   依据：若lmax <= rmax，则左侧位置接水量只由lmax决定
    //         对称地，若lmax > rmax，则右侧位置接水量只由rmax决定
    //   复杂度：时间O(n)，空间O(1)
    // 方法2: 单调栈 横着算
    //   栈中存的是下标(index)，不是高度值
    //   原因：计算宽度需要左右边界下标，宽 = 右索引 - 左索引 - 1
    //   形态：维护对应高度单调递减的下标栈
    //   思路：遇到更高柱子时弹出栈顶作为凹槽底部，配合新栈顶和当前柱计算面积
    //   计算：高 = min(左墙, 右墙) - 底部高度，宽 = 右索引 - 左索引 - 1
    //   复杂度：时间O(n)，空间O(n)
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int lmax = height[l];
        int rmax = height[r];
        int water = 0;
        while(l <= r){ //相等的时候一边指针提前来过,但是没算,要等两个指针都到了算一遍
            if(lmax <= rmax){
                //先对比高度,再算水
                lmax = Math.max(lmax, height[l]);
                //竖着算
                water += lmax - height[l];
                l++;
            }else{
                rmax = Math.max(rmax, height[r]);
                water += rmax - height[r];
                r--;
            }
        }
        System.out.println(l+" "+ r);
        return water;
    }

    //单调栈
    public int trap2(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int water = 0;
        //从左往右遍历 记住左边的高点 横向计算
        for(int i = 0; i < height.length; i++){
            //栈底大顶小：单调递减
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                int bottomIndex = stack.pop();
                if (stack.isEmpty()){
                    //左边没有高点了，排除这次计算
                    break;
                }
                //横向计算
                int curHei = Math.min(height[stack.peek()], height[i]) - height[bottomIndex];
                int curWei = i - stack.peek() - 1;
                water += curHei * curWei;
            }
            stack.push(i);
        }
        return water;
    }

}
