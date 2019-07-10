package dfstodp;

import java.util.ArrayList;
import java.util.List;

public class WordSquare {
    // Solution 1
    public List<List<String>> wordSquares(String[] words) {
        int size = words[0].length();
        List<List<String>> res = new ArrayList<>();
        dfs(words, size, new ArrayList<>(), res);
        return res;
    }

    private void dfs(String[] words, int remain, List<String> cur, List<List<String>> res) {
        if(remain == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for(String word : words) {
            int index = cur.size();
            boolean valid = true;
            for(int i = 0; i < cur.size() && valid; i++) {
                if(cur.get(i).charAt(index) != word.charAt(i)) valid = false;
            }
            if(valid) {
                cur.add(word);
                dfs(words, remain - 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
