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
}
