import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC496_hm_msta {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // nums2的值：答案
        Map<Integer, Integer> hm = new HashMap<>();
        Stack<Integer> s = new Stack<>();
        // 遍历nums2，答案存储到hm
        for (int i = 0; i < n2; i++) {
            while (!s.isEmpty() && nums2[i] > nums2[s.peek()]) {
                int index = s.pop();
                hm.put(nums2[index], nums2[i]);
            }
            s.push(i);
        }
        // 遍历nums1，取出答案
        int[] res = new int[n1];
        // Arrays.fill(res, -1);
        for (int i = 0; i < n1; i++) {
            res[i] = hm.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
