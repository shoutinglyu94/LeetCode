package twopointers;

import java.util.*;

public class TwoPointerBenchMark {

    public static void main(String[] args) {
//        int[][] board = new int[][] {
//                {110,5,  112,113, 114},
//                {210,211,5,  213, 214},
//                {310,311,3,  313, 314},
//                {410,411,412,5,   414},
//                {5,  1,  512,3,   3},
//                {610,4,  1,  613, 614},
//                {710,1,  2,  713, 714},
//                {810,1,  2,  1,   1},
//                {1,  1,  2,  2,   2},
//                {4,  1,  4,  4,   1014}
//        };
//
//        CandyCrush candyCrush = new CandyCrush();
//        candyCrush.candyCrush(board);
        shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}});
    }


    public static int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, List<Integer>> red = new HashMap<>();
        Map<Integer, List<Integer>> blue = new HashMap<>();
        for (int i = 0; i < n; i++) {
            red.put(i, new ArrayList<>());
            blue.put(i, new ArrayList<>());
        }
        for (int[] rededge : red_edges) {
            red.get(rededge[0]).add(rededge[1]);
        }

        for (int[] blueedge : blue_edges) {
            blue.get(blueedge[0]).add(blueedge[1]);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, -1});
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int color = cur[1];
                if (color == -1 || color == 0) {
                    List<Integer> bneis = blue.get(cur[0]);
                    for (int nei : bneis) {
                        if (dist[nei] == -1) {
                            dist[nei] = step + 1;
                            q.offer(new int[]{nei, 1});
                        }
                    }
                }
                if (color == -1 || color == 1) {
                    List<Integer> rneis = red.get(cur[0]);
                    for (int nei : rneis) {
                        if (dist[nei] == -1) {
                            dist[nei] = step + 1;
                            q.offer(new int[]{nei, 0});
                        }
                    }
                }
            }
        }
        return dist;
    }
}
