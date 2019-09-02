package graph.bfs;

import java.util.*;

public class WordLadder2 {
    public List<List<String>> wordLadder2(String start, String end, List<String> wordDict) {
        List<List<String>> res = new ArrayList<>();

        Map<String, List<String>> graph = new HashMap<>();
        graph.put(start, new ArrayList<>());

        Map<String, Integer> distance = new HashMap<>();
        // graph.bfs: find the shortest paths from start to end,
        // there could be more than one shortest paths.
        // record distance between nodes and start word
        // generate neighbors of nodes
        bfs(start, end, wordDict, graph, distance);

        List<String> curPath = new ArrayList<>();
        curPath.add(start);
        // DFS: construct shortest path with distance map and graph map
        dfs(start, end, graph, distance, res, curPath);
        return res;
    }

    private void bfs(String start, String end, List<String> wordDict,
                     Map<String, List<String>> graph, Map<String, Integer> distance) {
        for (String str : wordDict) {
            graph.put(str, new ArrayList<>());
        }
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(start);
        int level = 0;
        distance.put(start, level);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;
            for(int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> neis = getNeighbors(cur, wordDict);
                for (String nei : neis) {
                    // construct graph during expanding neighbor nodes
                    graph.get(cur).add(nei);
                    // check if we have visited the node
                    if (!distance.containsKey(nei)) {
                        distance.put(nei, level + 1);
                        if (nei.equals(end)) found = true;
                        else queue.offer(nei);
                    }
                }
            }
            // when we found the end word,
            // break after current level and record all paths with the same length
            if (found) {
                break;
            }
            level++;
        }
    }

    // 2 ways to get neighbors
    // loop every characters of the word
    // or loop every word in the word dictionary
    private List<String> getNeighbors(String word, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        char[] array = word.toCharArray();
        for(char c = 'a'; c <= 'z'; c++) {
            for(int i = 0; i < array.length; i++) {
                if(c == array[i]) continue;
                char origin = array[i];
                array[i] = c;
                String newWord = String.valueOf(array);
                if(wordDict.contains(newWord)) res.add(newWord);
                array[i] = origin;
            }
        }
        return res;
    }

    private void dfs(String start, String end, Map<String, List<String>> graph, Map<String, Integer> distance,
                     List<List<String>> res, List<String> curPath) {
        if (start.equals(end)) {
            res.add(new ArrayList<>(curPath));
            return;
        }
        for (String nei : graph.get(start)) {
            if (distance.get(nei) == distance.get(start) + 1) {
                curPath.add(nei);
                dfs(nei, end, graph, distance, res, curPath);
                curPath.remove(curPath.size() - 1);
            }
        }
    }

}
