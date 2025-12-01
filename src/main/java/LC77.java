import java.util.ArrayList;
import java.util.List;

public class LC77 {
    List<List<Integer>> res;
    List<Integer> row;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        row = new ArrayList<>();
        backTracking(n, k, 1);
        return res;
    }

    public void backTracking(int n, int k, int startNum){
        if(row.size() == k){
            res.add(new ArrayList<>(row));
            return;
            // System.out.println(row);
        }
        for(int i = startNum; i <= n - (k - row.size()) + 1; i++){
            row.add(i);
            backTracking(n, k, i + 1);
            // row.remove((Integer)i);
            row.removeLast();
        }

    }

}
