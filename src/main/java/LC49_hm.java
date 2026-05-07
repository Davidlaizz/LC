import java.util.*;

public class LC49_hm {
    // 方法1: 排序法
    //   思路：将字符串字符排序后作为key，异位词排序后相同，可归为一组
    //   复杂度：时间O(nklogk)，空间O(nk)
    //          n是字符串数量，k是字符串最大长度
    // 方法2: 计数法
    //   思路：统计每个字母出现次数，拼接成"字母+次数"字符串作为key
    //   复杂度：时间O(nk)，空间O(nk)
    //          n是字符串数量，k是字符串最大长度
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

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

}
