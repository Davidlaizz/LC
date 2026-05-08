import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC51H_bt {
    // 方法: 回溯(递归+撤销)
    //   目标：在n×n棋盘上放置n个皇后，使其互不攻击
    //   思路：按行放置，每行只放一个皇后，检查列和对角线冲突
    //   过程：遍历当前行的每一列，若isValid则放置并递归下一行
    //   isValid检查：列(上方)、45度(左上)、135度(右上)是否有皇后
    //   终止：row == n 时收集一种合法布局
    //   复杂度：时间O(n!)，空间O(n²)
    //          第一行n种选择，第二行最多n-1种，依次递减
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        // 初始化棋盘
        for(char[] row : board){
            Arrays.fill(row, '.');
        }
        backTracking(n, 0, 0, board);
        return res;
    }
    public void backTracking(int n, int row, int col, char[][] board){
        if(n == row){ // 每一行都放了Q
            res.add(Array2List(board));
            return;
        }
        for(int j = 0; j < n; j++){ //遍历本行的所有可能列
            if(isValid(n, row, j, board)){ //当前位置可以放皇后
                board[row][j] = 'Q';
                backTracking(n, row + 1, j, board);
                board[row][j] = '.'; //关键：回溯保证每行都只放一个Q
            }

        }
    }
    public boolean isValid(int n, int row, int col, char[][] board){
        // 检查列 : 列举前面的列
        for(int i = 0; i < row; i++){
            if(board[i][col] == 'Q')
                return false;
        }
        // 检查45度 ： 列举左上角
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--){
            if(board[i][j] == 'Q')
                return false;
        }
        // 检查135度 ： 列举右上角
        for(int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++){
            if(board[i][j] == 'Q')
                return false;
        }
        return true;
    }
    public List<String> Array2List(char[][] board){
        List<String> row = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int n = board.length;
        for(int i = 0; i < n; i++){
            sb.setLength(0);
            for(int j = 0; j < n; j++){
                sb.append(board[i][j]);
            }
            row.add(sb.toString());
        }
        return row;
    }
}
