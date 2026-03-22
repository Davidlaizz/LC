import java.util.*;

public class LC2050H_bfs_dfs_dp_heap {
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] indegree = new int[n + 1];
        int[] dp = new int[n + 1]; // 学习i需要dp[i]时间
        List<List<Integer>> adj = new ArrayList<>();
        // 初始化
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        // 邻接表
        for (int[] relation : relations) {
            indegree[relation[1]]++;
            adj.get(relation[0]).add(relation[1]); // prevCourse ->nextCourse
        }
        // BFS
        Deque<Integer> now = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                now.offer(i); // 当前课程没有前置任务，加入学习列表
            }
        }
        int res = 0;
        while (!now.isEmpty()) {
            int cur = now.poll();
            // 之前的dp[cur]是上cur这节课「之前」需要的时间，已经被他的前驱课程更新过
            dp[cur] += time[cur - 1]; // 这才是上完cur「之后」的时间
            res = Math.max(res, dp[cur]);
            // 继续更新以cur为前置的课程时间
            List<Integer> nextList = adj.get(cur);
            for (int next : nextList) {
                // 更新next,以最长前驱链的时间为准
                dp[next] = Math.max(dp[next], dp[cur]);
                // 减少他们的入度
                indegree[next]--;
                // 如果next刷完了前置课程，入学习队列
                if (indegree[next] == 0) {
                    now.offer(next);
                }
            }
        }
        return res;
    }

    // 优先队列小根堆能更好地根据时间模拟课程真实排序，
    // 时间复杂度：普通 Queue (方法1)： $O(V + E)$。因为普通的入队出队是 $O(1)$，极其迅速。
    // 优先队列 Heap (改版后)： $O(V \log V + E)$。因为每次把课程塞进堆里，都需要 $O(\log V)$ 的时间进行排序调整。
    // 空间复杂度： 都是 $O(V + E)$，建图的开销。
    // 结论：Queue + DP 省略了堆排序的开销。但是，Heap 模拟法在思维上是降维打击，它不需要你用脑子去绕 Math.max 的状态转移，完全符合直觉。
    public int minimumTime2(int n, int[][] relations, int[] time) {
        int[] indegree = new int[n + 1];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] relation : relations) {
            indegree[relation[1]]++;
            adj.get(relation[0]).add(relation[1]);
        }

        // 【核心改变1】：使用优先队列（小顶堆），按照课程的“结束时间”升序排列
        // 数组结构：int[]{ 课程结束的绝对时间, 课程编号 }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                // 没有前置任务的课，从第 0 个月开始上，结束时间就是它自己的耗时
                pq.offer(new int[]{time[i - 1], i});
            }
        }

        int res = 0;

        // 【核心改变2】：按时间线模拟
        while (!pq.isEmpty()) {
            int[] curEvent = pq.poll();
            int curTime = curEvent[0]; // 当前课程真实结束的时间点
            int curCourse = curEvent[1];

            // 记录整个时间线的最晚时间
            res = curTime;

            for (int next : adj.get(curCourse)) {
                indegree[next]--;
                // 当 next 的入度降为 0 时，说明它的最后一门前置课刚刚结束！
                // 此时的时间 curTime，天然就是 next 的开课时间！
                if (indegree[next] == 0) {
                    // next 的结束时间 = 当前时间 + next 自身的耗时
                    pq.offer(new int[]{curTime + time[next - 1], next});
                }
            }
        }
        return res;
    }
}
