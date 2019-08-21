package bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Maze {
    public int shortestPath(int[][] maze, int[][] starts, int[][] exits) {
        int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new ArrayDeque<>();
        for(int[] start : starts) {
            maze[start[0]][start[1]] = -1;
            queue.offer(start);
        }
        Set<Integer> ends = new HashSet<>();
        for(int[] exit : exits) {
            ends.add(exit[0] * maze[0].length + exit[1]);
        }
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for(int[] dir : dirs) {
                    int row = cur[0] + dir[0];
                    int col = cur[1] + dir[1];
                    if(maze[row][col] != -1 && maze[row][col] != 1) {
                        if(ends.contains(row * maze[0].length + col)) {
                            return level + 1;
                        }
                        queue.offer(new int[] {row, col});
                    }
                }
            }
            level++;
        }
        return -1;
    }

}
