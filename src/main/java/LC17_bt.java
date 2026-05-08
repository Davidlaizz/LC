import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17_bt {
    // 方法: 回溯(递归+撤销)
    //   目标：求数字字符串对应的所有字母组合
    //   思路：每个数字对应多个字母，需要枚举所有组合
    //   过程：用startIndex指向当前处理的数字位置
    //         遍历该数字对应的每个字母，加入路径后递归下一数字
    //   终止：sb.length() == digits.length() 时收集一个组合
    //   复杂度：时间O(4^n)，空间O(n)
    //          最坏每个数字对应4个字母，共4^n种组合
    List<String> res;
    Map<String, String> hm;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if(digits.equals(""))
            return res;
        hm = new HashMap<>();
        hm.put("2", "abc");
        hm.put("3", "def");
        hm.put("4", "ghi");
        hm.put("5", "jkl");
        hm.put("6", "mno");
        hm.put("7", "pqrs");
        hm.put("8", "tuv");
        hm.put("9", "wxyz");
        // 也可以用数组，初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        // String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder();
        backTracking(digits, 0, sb);
        return res;

    }
    public void backTracking(String digits, int startIndex, StringBuilder sb){
        // 中止条件
        if(sb.length() == digits.length()){
            res.add(sb.toString());
        }
        // 剪枝
        // if(startIndex >= digits.length()){
        //     return;
        // }

        for(int i = startIndex; i < digits.length(); i++){
            String cur = hm.get(String.valueOf(digits.charAt(i)));
            // System.out.println(cur);
            for(int j = 0; j < cur.length(); j++){
                sb.append(cur.charAt(j));
                backTracking(digits, i + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }

        }
    }
}
