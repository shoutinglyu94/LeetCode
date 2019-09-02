package graph.bfs;

import java.util.*;

public class MinimumHeightTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(n < 1) return res;
        if(n == 1) {
            res.add(0);
            return res;
        }
        int[] degrees = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        for(int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
            graph.putIfAbsent(edge[0],  new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for(int i = 0; i < n; i++) {
            if(degrees[i] == 1) {
                visited[i] = true;
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            res.clear();
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int cur = q.poll();
                res.add(cur);
                for(int nei : graph.get(cur)) {
                    if(!visited[nei]) {
                        visited[nei] = true;
                        q.offer(nei);
                    }
                }
            }
        }
        return res;
    }
}
