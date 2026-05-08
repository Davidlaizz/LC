import java.util.LinkedList;
import java.util.Queue;

public class LC994_bfs {
    // 方法: BFS多源最短路
    //   目标：计算所有新鲜橘子被感染所需的最少分钟数
    //   思路：初始时把所有腐烂橘子入队，作为BFS的起点
    //   过程：每分钟处理当前队列所有节点，向四个方向感染新鲜橘子
    //   记录：用fresh计数剩余新鲜橘子，每轮结束后minutes++
    //   终止：队列为空时结束；若fresh > 0则返回-1（存在无法被感染的橘子）
    //   复杂度：时间O(mn)，空间O(mn)
    //          最坏所有橘子都入队
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int minutes = 0;
        int fresh = 0;
        Queue<int[]> q = new LinkedList<>();

        // 统计新鲜橘子数量，腐烂橘子入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                }
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        // 方向数组：上、下、左、右
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // BFS感染
        while (!q.isEmpty()) {
            int size = q.size();
            boolean infected = false;

            for (int i = 0; i < size; i++) {
                int[] p = q.poll();
                for (int k = 0; k < 4; k++) {
                    int x = p[0] + dx[k];
                    int y = p[1] + dy[k];

                    // 边界检查 + 只感染新鲜橘子
                    if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        fresh--;
                        q.offer(new int[]{x, y});
                        infected = true;
                    }
                }
            }

            if (infected) {
                minutes++;
            }
        }

        // 还有新鲜橘子无法被感染
        if (fresh > 0) {
            return -1;
        }

        return minutes;
    }
}
