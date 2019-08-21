package twopointers;

import java.util.ArrayList;
import java.util.List;

public class CandyCrush {

    List<Integer> target;
    public int[][] candyCrush(int[][] board) {
        target = new ArrayList<>();
        int i = 0;
        while(!check(board)) {
            System.out.println("Round" + i);
            crush(board);
            drop(board);
            target.clear();
            show(board);
            i++;
        }
        return board;
    }
    private void show(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "   ");
            }
            System.out.println("\n");
        }
    }


    private boolean check(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        // check rows
        for(int row = 0; row < n; row++) {
            int i = 0, j = 0;
            while(j <= m) {
                if(j == m || board[row][j] != board[row][i]) {
                    if(j - i >= 3 && board[row][i] != 0) {
                        mark(row, i, j, m, true);
                    }
                    i = j;
                }
                j++;
            }
        }

        // check cols
        for(int col = 0; col < m; col++) {
            int i = 0, j = 0;
            while(j <= n) {
                if(j == n || board[j][col] != board[i][col]) {
                    if(j - i >= 3 && board[i][col] != 0) {
                        mark(col, i, j, m, false);
                    }
                    i = j;
                }
                j++;
            }
        }
        return target.isEmpty();
    }

    private void mark(int x, int start, int end, int len, boolean markRow) {
        if(markRow) {
            for(int col = start; col < end; col++) {
                target.add(x * len + col);
            }
        } else {
            for(int row = start; row < end; row++) {
                target.add(row * len + x);
            }
        }

    }

    private void crush(int[][] board) {
        int m = board[0].length;
        for(int code : target) {
            int row = code / m;
            int col = code % m;
            board[row][col] = 0;
        }
    }

    private void drop(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        for(int col = 0; col < m; col++) {
            int i = n - 1;
            for(int j = n - 1; j >= 0; j--) {
                if(board[j][col] != 0) {
                    board[i--][col] = board[j][col];
                }
            }
            while(i >= 0) {
                board[i--][col] = 0;
            }
        }
    }

}
