import java.util.ArrayList;
import java.util.List;

public class LC131_bt {
    List<List<String>> res = new ArrayList<>();
    List<String> row = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return res;
    }

    public void backTracking(String s, int startIndex){
        if(s.length() == startIndex){
            //注意创建一个新的copy
            res.add(new ArrayList<>(row));
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isValid(s, startIndex, i)){
                // System.out.println(s.substring(startIndex, i + 1));
                row.add(s.substring(startIndex, i + 1)); //左闭右开
                backTracking(s, i + 1);
                row.remove(row.size() - 1); // 移除栈顶元素
            }
        }
    }
    // [startIndex, endIndex]
    public boolean isValid(String s, int startIndex, int endIndex){
        // s = s.substring(startIndex, endIndex + 1);
        while(startIndex < endIndex){
            if(s.charAt(startIndex) == s.charAt(endIndex)){
                startIndex++;
                endIndex--;
            }else{
                return false;
            }
        }
        return true;
    }
}
