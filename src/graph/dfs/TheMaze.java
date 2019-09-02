package graph.dfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TheMaze {
    private static final String UP = "u";
    private static final String DOWN = "d";
    private static final String LEFT = "l";
    private static final String RIGHT = "r";
    private String res = null;
    int[][] dirs;
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] dist = new int[m][n];
        String[][] path = new String[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            Arrays.fill(path[i], "");
        }

        Queue<int[]> q = new ArrayDeque<>();
        dist[ball[0]][ball[1]] = 0;
        q.offer(ball);

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            for(int[] dir : dirs) {
                String newPath = path[r][c] + getDir(dir);
                int[] nei = new int[] {r, c, 0};
                if(getNei(m, n, nei, dir, hole, maze, dist, path, newPath)) {
                    continue;
                }
                if(shorter(r, c, nei[0], nei[1], nei[2], newPath, dist, path)) {
                    dist[nei[0]][nei[1]] = dist[r][c] + nei[2];
                    path[nei[0]][nei[1]] = newPath;
                    q.offer(new int[] {nei[0], nei[1]});
                }
            }
        }

        return res;
    }

    private boolean getNei(int m, int n, int[] nei, int[] dir, int[] hole, int[][] maze, int[][] dist, String[][] path, String newPath) {
        int r = nei[0];
        int c = nei[1];
        int cnt = 0;
        int nr = r;
        int nc = c;
        while(valid(nr, nc, m, n) && maze[nr][nc] != 1) {
            nr += dir[0];
            nc += dir[1];
            cnt++;
            if(meetHole(nr, nc, hole)
                    && shorter(r, c, nr, nc, cnt, newPath, dist, path)) {
                dist[nr][nc] = dist[r][c] + cnt;
                path[nr][nc] = newPath;
                res = newPath;
                //System.out.println(newPath);
                //System.out.println(dist[nr][nc]);
                return true;
            }
        }
        nr -= dir[0];
        nc -= dir[1];
        nei[0] = nr;
        nei[1] = nc;
        nei[2] = cnt - 1;
        return false;
    }

    private String getDir(int[] dir) {
        if(dir[0] == 1 && dir[1] == 0) {
            return DOWN;
        } else if (dir[0] == -1 && dir[1] == 0) {
            return UP;
        } else if (dir[0] == 0 && dir[1] == -1) {
            return LEFT;
        } else {
            return RIGHT;
        }
    }

    private boolean shorter(int r, int c, int nr, int nc, int cnt, String newPath, int[][] dist, String[][] path) {
        return dist[r][c] + cnt < dist[nr][nc] ||
                (dist[r][c] + cnt == dist[nr][nc] && newPath.compareTo(path[nr][nc]) < 0);
    }

    private boolean meetHole(int r, int c, int[] hole) {
        return r == hole[0] && c == hole[1];
    }

    private boolean valid(int r, int c, int m, int n) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }
}
