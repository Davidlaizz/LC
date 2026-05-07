public class LC11_gr_2p {
    // 方法: 贪心 + 双指针
    //   思路：左右指针形成容器，面积由短板和宽度决定
    //   贪心：每次移动短板指针，才可能得到更大面积
    //         移动长板只会减小宽度，且短板不变，面积不会变大
    //   复杂度：时间O(n)，空间O(1)
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
