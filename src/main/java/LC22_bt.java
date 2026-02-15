import java.util.ArrayList;
import java.util.List;

public class LC22_bt {
    List<String> res = new ArrayList<>();
    StringBuilder row = new StringBuilder();
    public List<String> generateParenthesis(int n) {
        backTracking(2 * n, 0, 0);
        return res;
    }

    public void backTracking(int n, int left, int right) {
        if (row.length() == n) {
            res.add(row.toString());
            return;
        }
        // 左括号得控制数量
        if (left < n / 2) {
            row.append("(");
            backTracking(n, left + 1, right);
            row.deleteCharAt(row.length() - 1);
        }
        // 右括号必须先有左括号
        if (left > right){
            row.append(")");
            backTracking(n, left, right + 1);
            row.deleteCharAt(row.length() - 1);
        }

    }
}
