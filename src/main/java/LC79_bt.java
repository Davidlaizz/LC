public class LC79_bt {
    // 方法: 回溯(DFS)
    //   目标：在二维网格中搜索单词是否存在
    //   思路：遍历每个格子作为起点，DFS四个方向匹配单词
    //   状态：idx表示当前匹配到单词的第几个字符
    //   访问标记：用board[row][col] = '0'标记已访问，回溯时恢复原值
    //   剪枝：越界、字符不匹配、已找到结果时提前返回
    //   终止：idx == word.length() - 1 且字符匹配时成功
    //   复杂度：时间O(mn * 4^L)，空间O(L)
    //          mn是网格大小，L是单词长度
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
