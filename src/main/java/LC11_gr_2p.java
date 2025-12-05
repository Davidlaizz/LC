public class LC11_gr_2p {
    public int maxArea(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1;
        //贪心:能盛多少水由短板决定,尝试更新短板
        while(l < r){
            //找短板
            int low = Math.min(height[l], height[r]);
            res = Math.max(low * (r - l), res);
            //抛弃短板
            if(low == height[l]){
                l++;
            }else{
                r--;
            }
        }
        return res;
    }
}
