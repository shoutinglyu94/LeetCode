package string;

import java.util.*;

public class GroupAllAnagram {
    // Sort Time O(n * log(len of string))
    public List<List<String>> group(List<String> input) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : input) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String sortKey = new String(array);
            map.putIfAbsent(sortKey, new ArrayList<>());
            map.get(sortKey).add(s);
        }
        List<List<String>> res = new ArrayList<>();
        for(String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    // Count


}
