import java.util.*;

public class LC49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> ht = new HashMap<>();
        for(String str : strs){
            char[] array = str.toCharArray();
            Arrays.sort(array);
            // String key = array.toString(); 没有重写toString，默认返回的是哈希值
            String key = new String(array);
//            String key = Arrays.toString(array);
            // System.out.println(key);
            //取出key对应列表，赋值后放回
            List<String> list = ht.getOrDefault(key, new ArrayList<>());
            list.add(str);
            ht.put(key, list);
        }
        return new ArrayList<>(ht.values());
    }
}
