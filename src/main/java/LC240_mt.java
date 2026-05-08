import java.util.Arrays;

public class LC240_mt {
    // 方法1: 逐行二分
    //   思路：对每一行做二分查找，找到即返回true
    //   特点：实现直接，复用库函数binarySearch
    //   复杂度：时间O(m log n)，空间O(1)
    //
    // 方法2: 右上角阶梯搜索(推荐)
    //   思路：从右上角出发
    //         若当前值 < target，则下移(整行左侧都更小可排除)
    //         若当前值 > target，则左移(整列下方都更大可排除)
    //   关键：每一步都能排除一整行或一整列
    //   复杂度：时间O(m + n)，空间O(1)
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
