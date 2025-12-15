public class LC48_mt {
    public void rotate(int[][] matrix) {
        int left = 0;
        int right = matrix.length - 1;
        while(left < right){
            for(int i = 0; i < right - left; i++){
                int top = left;
                int buttom = right;
                //存储左上
                int leftTop = matrix[top][left + i];
                //左下给左上
                matrix[top][left + i] = matrix[buttom - i][left];
                //右下给左下
                matrix[buttom - i][left] = matrix[buttom][right - i];
                //右上给右下
                matrix[buttom][right - i] = matrix[top + i][right];
                //左上给右上
                matrix[top + i][right] = leftTop;
            }
            left++;
            right--;
        }
    }
}
