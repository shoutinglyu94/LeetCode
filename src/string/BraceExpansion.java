package string;

import java.util.*;

public class BraceExpansion {
    public List<String> braceExpansionII(String expression) {
        return dfs(expression, 0, expression.length() - 1);
    }

    private List<String> dfs(String s, int start, int end) {
        List<List<List<String>>> curLevel = new ArrayList<>();
        int level = 0;
        int nextStart = start;
        curLevel.add(new ArrayList<>());
        for(int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if(c == '{') {
                if(level == 0) {
                    List<List<String>> cur = curLevel.get(curLevel.size() - 1);
                    cur.add(new ArrayList<>());
                    nextStart = i + 1;
                }
                level++;
            } else if (c == '}') {
                level--;
                if(level == 0) {
                    List<List<String>> cur = curLevel.get(curLevel.size() - 1);
                    cur.get(cur.size() - 1).addAll(dfs(s, nextStart, i - 1));
                }
            } else if (c == ',' && level == 0) {
                curLevel.add(new ArrayList<>());
            } else if(level == 0) {
                int j = i;
                while(i <= end && Character.isLetter(s.charAt(i))) {
                    i++;
                }
                List<List<String>> cur = curLevel.get(curLevel.size() - 1);
                cur.add(new ArrayList<>());
                cur.get(cur.size() - 1).add(s.substring(j, i));
                i--;
            }
        }
        Set<String> set = new HashSet<>();
        for(int i = 0; i < curLevel.size(); i++) {
            set.addAll(merge(curLevel.get(i)));
        }
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

    private List<String> merge(List<List<String>> lists) {
        List<String> res = new ArrayList<>();
        if(lists.size() == 1) {
            res.addAll(lists.get(0));
            return res;
        }
        for(int i = 1; i < lists.size(); i++) {
            res = mergeTwoList(i == 1 ? lists.get(0) : res, lists.get(i));
        }
        return res;

    }

    private List<String> mergeTwoList(List<String> a, List<String> b) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                res.add(a.get(i) + b.get(j));
            }
        }
        return res;
    }
}
