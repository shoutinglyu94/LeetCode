package graph;

import java.util.*;

public class FindPathIn2DArray {

    private int n;
    private final int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public List<int[]> shortestPath(int[][] matrix, int[] start, int[] end) {
        List<int[]> res = new LinkedList<>();
        n = matrix.length;
        Queue<int[]> q = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(code(start[0], start[1]), -1);
        q.offer(start);
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == end[0] && cur[1] == end[1]) {
                break;
            }
            for(int[] dir : dirs) {
                int row = cur[0] + dir[0];
                int col = cur[1] + dir[1];
                if(valid(row, col, matrix) &&
                        !map.containsKey(code(row, col))) {
                    map.put(code(row ,col), code(cur[0], cur[1]));
                    q.offer(new int[] {row, col});
                }

            }
        }
        int code = code(end[0], end[1]);
        if(!map.containsKey(code)) {
            return res;
        }
        res.add(end);
        while(map.get(code) != -1) {
            int pcode = map.get(code);
            int[] parent = new int[2];
            decode(parent, pcode);
            res.add(0, parent);
            code = pcode;
        }
        return res;
    }

    private boolean valid(int row, int col, int[][] matrix) {
        return row < n && row >= 0 && col < n && col >= 0 && matrix[row][col] != -1;
    }

    private int code(int row, int col) {
        return n * row + col;
    }

    private void decode(int[] parent, int code) {
        parent[0] = code / n;
        parent[1] = code % n;
    }



}
