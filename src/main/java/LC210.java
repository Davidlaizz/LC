import java.util.*;

class LC210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            adjacency.add(new ArrayList<>());
        }
        for(int[] pre : prerequisites){
            indegrees[pre[0]]++;
            adjacency.get(pre[1]).add(pre[0]);
        }
        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0){
                queue.add(i);
            }
        }
        int[] res = new int[numCourses];
        int cnt = 0;
        while(!queue.isEmpty()){
            int cur = queue.remove();
            res[cnt] = cur;
            cnt++;
            for(int next : adjacency.get(cur)){
                indegrees[next]--;
                if(indegrees[next] == 0){
                    queue.add(next);
                }
            }
        }
        return cnt == numCourses ? res : new int[]{};
    }
}