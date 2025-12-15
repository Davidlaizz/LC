import java.util.Arrays;

public class LC240_mt {
    public boolean searchMatrix(int[][] matrix, int target) {
        //逐行寻找 + 二分
        for(int[] row : matrix){
            int idx = Arrays.binarySearch(row, target);
            if(idx >= 0){
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        //利用矩阵特性,取上和右的边
        int startRow = 0, startCol = matrix[0].length - 1;
        while(startRow < matrix.length && startCol >= 0){
            if(matrix[startRow][startCol] == target)
                return true;
            else if(matrix[startRow][startCol] < target)
                startRow++;
            else
                startCol--;
        }
        return false;
    }
}
