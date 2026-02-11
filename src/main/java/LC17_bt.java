import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17_bt {
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
