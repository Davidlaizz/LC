public class LC278_bs {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                left = mid + 1;
            } else {
                if (mid == 1 || isBadVersion(mid - 1)){
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return 0;
    }

    boolean isBadVersion(int version) {
        return true;
    }
}
