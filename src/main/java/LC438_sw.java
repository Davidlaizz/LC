import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC438_sw {
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            int ns = s.length();
            int np = p.length();
            if(np > ns)
                return res;
//                return List.of();
            int sCount[] = new int[26];
            int pCount[] = new int[26];
            //初始化pCount
            for(int i = 0; i < np; i++){
                pCount[p.charAt(i) - 'a']++;
            }
            //初始化sCount
            for(int i = 0; i < np; i++){
                sCount[s.charAt(i) - 'a']++;
            }
            if(Arrays.equals(pCount, sCount)){
                res.add(0);
            }
            for(int l = 0, r = np; r < ns; l++, r++){
                sCount[s.charAt(r) - 'a']++;
                sCount[s.charAt(l) - 'a']--;
                if(Arrays.equals(pCount, sCount)){
                    res.add(l + 1);
                }
            }
            return res;
        }
    }
}
