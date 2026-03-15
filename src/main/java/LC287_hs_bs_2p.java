public class LC287_hs_bs_2p {
    // 解法1：原地哈希  不修改 数组
    // 解法2：二分 找n/2前的出现次数 O（nlgn）
    // 解法3：快慢指针，难想

    // 原地哈希 On
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        // nums[i] 对应下标为nums[i] - 1
        // 关注nums[n - 1]
        int i = 0;
        while (i < n) {
            swap(nums, n - 1, nums[n - 1] - 1);
            i++;
        }
        // System.out.println(Arrays.toString(nums));
        return nums[n - 1];
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 二分 Onlgn
    public int findDuplicate2(int[] nums) {
        int n = nums.length;
        int l = 1, r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 前mid数应该=mid个,否则就是重复数
            if (count(nums, mid) <= mid) {
                // [1, mid - 1] 没有问题
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            // System.out.println(mid + " " + count(nums, mid));
        }
        return l;
    }
    public int count(int[] nums, int val) {
        int cnt = 0;
        for (int num : nums) {
            if (num <= val) {
                cnt++;
            }
        }
        return cnt;
    }

    // 快慢指针,判断链表的环点(难想到) On
    public int findDuplicate3(int[] nums) {
        int fast = 0, slow = 0;
        // 一定有环
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (fast == slow) {
                break;
            }
        }
        // 找到环后,参考LC141,142
        // 让快指针变慢 a = b + nc n>=1
        fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[fast];
            if (fast == slow) {
                break;
            }
        }
        return fast;
    }
}
