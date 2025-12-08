import java.util.Deque;
import java.util.LinkedList;

public class LC239H_sw_md {
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int res[] = new int[n - k + 1];
            //单调队列 头大尾小 存Index
            Deque<Integer> d = new LinkedList<>();
            //初始化单调队列
            for(int i = 0; i < k; i++){
                while(!d.isEmpty() && nums[d.peekLast()] <= nums[i]){
                    d.removeLast();
                }
                d.addLast(i);
            }

            res[0] = nums[d.peekFirst()];
            //遍历剩余部分
            for(int i = k; i < n; i++){
                //更新队列,淘汰过期
                //从队首开始淘汰,至少保证队首是有效的,允许队中有过期数据,反正到队首会被淘汰
                while(!d.isEmpty() && d.peekFirst() <= i - k){
                    d.removeFirst();
                }
                //添加当前元素
                while(!d.isEmpty() && nums[d.peekLast()] <= nums[i]){
                    d.removeLast();
                }
                d.addLast(i);
                //此时最大值为窗口最大值
                res[i - k + 1] = nums[d.peekFirst()];
            }
            return res;
        }
    }
}
