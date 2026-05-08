class LC200 {
    // 方法: DFS染色
    //   思路：遍历网格，遇到'1'时岛屿数+1，并用DFS把整个岛屿染成'2'
    //   染色：将已访问的'1'改成'2'（或其他标记），避免重复计数
    //   方向：上下左右四个方向递归
    //   终止：越界或当前格子不是'1'
    //   复杂度：时间O(mn)，空间O(mn)
    //          最坏递归深度可达mn
    //
    // 方法2: BFS染色(可选)
    //   思路：用队列做层序遍历，把相邻'1'全部标记
    //   复杂度：时间O(mn)，空间O(min(m,n))
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(grid[i][j] == '1'){
                    count ++;
                }
                dfs(i, j , grid);
            }

        }
        return count;
    }

    public void dfs(int i, int j, char[][] grid){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '2';
        dfs(i-1, j, grid); //shang
        dfs(i+1, j, grid); //xia
        dfs(i, j-1, grid); //zuo
        dfs(i, j+1, grid); //you
    }
}