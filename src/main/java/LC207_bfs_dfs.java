import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date: 2025/11/30
 */
class LC207_bfs_dfs {
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        //初始化
        int indegrees[] = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adjacency.add(new ArrayList<>());
        }
        Queue<Integer> q = new LinkedList<>();

        for(int[] pre : prerequisites){
            indegrees[pre[0]]++;//有前置课程就添加 入度
            adjacency.get(pre[1]).add(pre[0]); //pre[1]->pre[0]的单向边
        }

        //BFS
        //可以直接学的课程
        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0){
                q.offer(i);
            }
        }
        int cnt = 0; //学习的课程的数量
        while(!q.isEmpty()){
            int cur = q.poll();
            cnt++;
            //学习cur后，改变以cur为前置条件的课程的入度
            for(int next : adjacency.get(cur)){
                //若为0，则学完了前置课程，把cur加入队尾
                if(--indegrees[next] == 0){
                    q.offer(next);
                }
            }

        }
        return cnt == numCourses;
    }

    /**
     * 0: 未访问
     * 1: 正在访问中（当前递归栈中的节点）
     * 2: 已完成访问（安全的，无环）
     */
    private int[] visited;
    private List<List<Integer>> adjacency;
    private boolean hasCycle = false; // 全局变量，用于标记是否找到环

    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // 1. 初始化
        // 初始化 visited 数组
        visited = new int[numCourses];

        // 初始化邻接表 adjacency
        adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adjacency.add(new ArrayList<>());
        }

        // 构建邻接表
        // pre[1] -> pre[0] 的单向边
        for(int[] pre : prerequisites){
            adjacency.get(pre[1]).add(pre[0]);
        }

        // 2. 遍历所有课程，进行 DFS
        // 对每个未访问的节点都进行一次 DFS 探索
        for(int i = 0; i < numCourses; i++){
            if(visited[i] == 0){
                dfs(i);
            }
        }

        // 3. 结果判断
        // 如果找到了环 (hasCycle 为 true)，则不能完成所有课程，返回 false
        return !hasCycle;
    }

    /**
     * 深度优先搜索函数
     * @param u 当前正在访问的课程节点
     */
    private void dfs(int u) {
        // 标记当前节点正在访问中（递归栈中）
        visited[u] = 1;

        // 遍历 u 的所有后继节点 (即 u 是 next 的前置课程)
        for (int next : adjacency.get(u)) {
            if (hasCycle) return; // 提前退出，若已找到环

            if (visited[next] == 0) {
                // 如果 next 未访问，继续向下深度探索
                dfs(next);
            } else if (visited[next] == 1) {
                // 如果 next 正在访问中，说明遇到了回边，存在环
                hasCycle = true;
                return;
            }
            // visited[next] == 2 的情况：说明 next 已经被访问且安全（无环），跳过即可
        }

        // 当前节点 u 的所有邻居都已访问完毕且无环，u 安全退出递归栈
        visited[u] = 2;
    }
}