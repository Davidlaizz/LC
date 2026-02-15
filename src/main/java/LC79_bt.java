public class LC79_bt {
    boolean res = false;
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                backTracking(board, word, 0, i, j);
                if(res){
                    return true;
                }
            }
        }
        return false;
    }
    public void backTracking(char[][] board, String word, int idx, int row, int col){
        if(res == true || row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(idx)){
            return;
        }
        if(idx == word.length() - 1){ // 最后一个元素也相同
            res = true;
            return;
        }
        char tmp = board[row][col];
        // 防止重复计算，或者使用if (!visited[newi][newj])
        board[row][col] = '0';
        // 上
        backTracking(board, word, idx + 1, row - 1, col);
        // 下
        backTracking(board, word, idx + 1, row + 1, col);
        // 左
        backTracking(board, word, idx + 1, row, col - 1);
        // 右
        backTracking(board, word, idx + 1, row, col + 1);
        // 回溯
        board[row][col] = tmp;
    }
}
