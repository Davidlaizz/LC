public class LC76H_sw {
    class Solution {
        public String minWindow(String s, String t) {
            int ns = s.length();
            int nt = t.length();
            if(nt > ns) return "";

            // 大写字母'A' - 'Z'65 - 90'A' 是 65;小写字母'a' - 'z'97 - 122'a' 是 97
            int needChar[] = new int[123];
            char[] cs = s.toCharArray();
            char[] ct = t.toCharArray();
            // 需要的字符
            for(char c : ct){
                needChar[c]++;
            }
            // 需要的数量
            int needCnt = nt;
            int resLen = Integer.MAX_VALUE;
            int resIndex = 0;
            for(int l = 0, r = 0; r < ns; r++){
                //处理字符
                if(needChar[cs[r]] > 0){
                    needCnt--; //正中下怀
                }
                needChar[cs[r]]--; //负数为多余,不重要

                //符合条件,找更小的情况
                while(needCnt == 0){
                    if(r - l + 1 < resLen){ //测试用例保证答案唯一,不考虑等于的情况
                        resLen = r - l + 1;
                        resIndex = l;
                    }
                    //尝试缩小l,寻找更小的情况
                    needChar[cs[l]]++;
                    if(needChar[cs[l]] > 0){
                        needCnt++; //如果>0说明不符合情况,继续r的循环
                    }
                    l++;
                }
            }
            if(resLen == Integer.MAX_VALUE){
                return "";
            }else{
                return s.substring(resIndex, resIndex + resLen); //左闭右开
            }
        }
    }
}
