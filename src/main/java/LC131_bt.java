import java.util.ArrayList;
import java.util.List;

public class LC131_bt {
    // 方法: 回溯(递归+撤销)
    //   目标：将字符串分割成所有可能的回文子串组合
    //   思路：用startIndex控制分割起点，枚举所有可能的分割位置
    //   过程：从startIndex开始尝试不同长度的子串，若是回文则加入路径并递归
    //   判断回文：双指针从两端向中间比较，isValid([startIndex, endIndex])
    //   终止：startIndex == s.length() 时收集一个完整分割方案
    //   复杂度：时间O(n * 2^n)，空间O(n)
    //          最坏每个位置都可分割，共2^n种方案，每种需O(n)判断回文
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
