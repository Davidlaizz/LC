public class LC74_bs {
    public boolean searchMatrix(int[][] matrix, int target) {
        int low = 0, top = matrix.length - 1;
        while(low <= top) {
            int mid = low + (top - low) / 2;
            if (matrix[mid][0] == target) {
                return true;
            }else if (matrix[mid][0] < target) {
                low = mid + 1;
            }else {
                top = mid - 1;
            }
        }
        // 标准二分查找 如果找不到则返回应该插入的位置，所以要-1F
        if (low <= 0)
            return false;
        else
            low--;
        // System.out.println(low);
        int left = 0, right = matrix[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[low][mid] == target) {
                return true;
            }else if (matrix[low][mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }
}
