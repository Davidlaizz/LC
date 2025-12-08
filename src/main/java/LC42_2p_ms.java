import java.util.Deque;
import java.util.LinkedList;

public class LC42_2p_ms {
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
