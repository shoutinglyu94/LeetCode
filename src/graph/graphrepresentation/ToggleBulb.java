package graph.graphrepresentation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ToggleBulb {

    class State {
        int[][] matrix;
        int index;
        int sum;
        State(int[][] matrix, int index, int sum) {
            int n = matrix.length;
            this.matrix = new int[n][n];
            for(int i = 0; i < n; i++) {
                this.matrix[i] = Arrays.copyOf(matrix[i], n);
            }
            this.index = index;
            this.sum = sum;
        }
    }
    public int shortestToggleBulb2(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(matrix, -1, 0));
        int step = 0;
        // Level by level BFS find shortest path.
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                State cur = queue.poll();
//                for(int k = 0; k < n; k++) {
//                    for(int l = 0; l < n; l++) {
//                        System.out.print(cur.matrix[k][l]);
//                    }
//                    System.out.print("\n");
//                }
                if(cur.sum == n * n) return step;
                for(int j = cur.index + 1; j < n * n; j++) {
                    int delta = toggle(cur.matrix, j);
                    queue.offer(new State(cur.matrix, j, cur.sum + delta));
                    toggle(cur.matrix, j);
                }
            }
            step++;
        }
        return -1;
    }



    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int shortestToggleBulb(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        int[] min = new int[] {n * n + 1};
        // DFS find shortest path.
        dfs(matrix, min, 0, 0);
        return min[0];
    }

    private void dfs(int[][] cur, int[] min, int index, int sum) {
        int n = cur.length;
        if(index == n * n || index > min[0]) {
            return;
        }
        if(sum == n * n) {
            min[0] = Math.min(min[0], index + 1);
            return;
        }

        for(int i = index; i < n * n; i++) {
            int delta = toggle(cur, i);
            dfs(cur, min, index + 1, sum + delta);
            toggle(cur, i);
        }
    }

    // Toggle bulb at coordinate (t / n,  t % n).
    private int toggle(int[][] matrix, int t) {
        int r = t / matrix.length;
        int c = t % matrix.length;
        int delta = 0;
        for(int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nr < matrix.length && nc >= 0 && nc < matrix.length) {
                if(matrix[nr][nc] == 0) {
                    delta++;
                    matrix[nr][nc] = 1;
                } else {
                    delta--;
                    matrix[nr][nc] = 0;
                }
            }
        }
        return delta;
    }

}
