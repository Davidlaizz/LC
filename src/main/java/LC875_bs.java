public class LC875_bs {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = -1;
        // 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
        // h 一定大于 piles.length 才能保证有解, 此时最大的right是piles的最大值，再大也没有意义
        for (int pile : piles) {
            right = Math.max(pile, right);
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (!canEatAll(piles, h, mid)) {
                left = mid + 1;
            } else {
                if (mid == 1 || !canEatAll(piles, h, mid - 1)) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    public boolean canEatAll(int[] piles, int h, int speed) {
        for (int pile : piles) {
            h -= (pile + speed - 1) / speed; // 向上取整
        }
        return h >= 0;
    }
}
