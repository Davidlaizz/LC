import java.util.ArrayList;
import java.util.List;

public class LC22_bt {
    // 方法: 回溯(递归+撤销)
    //   目标：生成n对括号的所有有效组合
    //   思路：用left/right记录已用左右括号数量
    //   约束1：左括号数量不能超过n（left < n）
    //   约束2：右括号数量不能超过左括号（left > right 才能选右）
    //   终止：row.length() == 2n 时收集结果
    //   复杂度：时间O(4^n/√n)，空间O(n)
    //          这是卡特兰数，第n项约为4^n/(n√n)
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
