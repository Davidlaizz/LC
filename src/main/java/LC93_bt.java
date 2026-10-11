import java.util.ArrayList;
import java.util.List;

public class LC93_bt {
    public List<String> restoreIpAddresses(String s) {
        if(s.length() > 12 || s.length() < 4){
//            return List.of();
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        backTracking(s, 0, 0, res);
        return res;
    }

    public void backTracking(String s, int startIndex, int pointSum, List<String> res){
        if(pointSum == 3){
            if(isValid(s, startIndex, s.length() - 1)){
                res.add(s);
                // System.out.println(s);
            }
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isValid(s, startIndex, i)){
                //[startIndex, i]满足ip条件，加入'.'
                String old = s;
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointSum++;
                backTracking(s, i + 2, pointSum, res);
                //还原回溯
                pointSum--;
                s = old;
            }else{
                break; //一个不合法全部不合法
            }
        }
        return;
    }

    public boolean isValid(String s, int startIndex, int endIndex){
        if(endIndex - startIndex > 4) return false;
        // if(s.length() - 1 < endIndex + 1){ //substring左比右开 防止越界
        //     s = s.substring(startIndex);
        // }else{
        s = s.substring(startIndex, endIndex + 1);
        // }
        if(s == "")
            return false;
        //排除0x的情况
        if(s.length() >= 2 && s.charAt(0) == '0')
            return false;
        //排除太大太小
        int num = Integer.parseInt(s);
        if(num > 255){
            return false;
        }
        return true;
    }
}
