import com.sun.javafx.scene.traversal.Direction;

import java.util.Random;

public class Game2048 {
    /*
https://play2048.co/

2048 is a game played on a 4x4 grid of tiles containing integer values (powers of 2).
The game begins with two tiles of 2 in random positions in the grid.. From 1point 3acres bbs
. 1point3acres
Operations:
- Tiles can move in one direction at a time: Up, Down, Left, or Right
- Tiles shift as far in that direction as possible
- When two tiles with the same number touch, they merge into one tile whose value is their sum
  - No cascading: (4 2 2 _) + L = (4 4 _ _)
- After a move operation, a new tile is generated in a random spot in the grid, with value 2

End conditions:
- Grid contains the value 2048; or
- All tiles cannot move in any direction
*/
    private int[][] board;
    private Random random;
    private boolean[] nextValidDirection;
    private static final int[][] DIRECTIONS  = new int[][] {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    public Game2048() {
        board = new int[4][4];
        nextValidDirection = new boolean[4];
        newGame();
    }

    private void newGame() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != 0) {
                    board[i][j] = 0;
                }
            }
        }
        randomGenerateNumber();
    }

    private void randomGenerateNumber() {
        random = new Random();
        int row = random.nextInt(board.length);
        int col = random.nextInt(board.length);
        board[row][col] = 2;
    }

    private void play(int dirIndex) {
        // Merge the number to one direction
        // Case 1: if we can not merge, then return
        if(!nextValidDirection[dirIndex]) return;

        // Case 2: else if we merge a 2048 then we win the game.
        if(merge(dirIndex)) {
            System.out.println("You Win!!!");
        }
        // Case 3: else finish this move

        // Generate new 2 at empty position.
        randomGenerateNumber();
        show();
        // Check Lose
        checknextValidDirection();
        int count = 0;
        for(boolean dir : nextValidDirection) {
            if(!dir) count++;
        }
        if(count == 4) {
            // Lost the game
            System.out.println("You Lost!!!!");
        } else {
            // Continue the game
            System.out.println("Next Move Please!");
        }
    }

    private void show() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    private boolean merge(int dir) {
        int[] row = new int[4];

    }


    private void checknextValidDirection() {
        int count = 0;
        OutLoop: for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(count == 4) return;
                for(int k = 0; k < DIRECTIONS.length; k++) {
                    int x = i + DIRECTIONS[k][0];
                    int y = j + DIRECTIONS[k][1];
                    if(!nextValidDirection[k] && valid(x, y) && (board[x][y] == 0 || board[x][y] == board[i][j])) {
                        nextValidDirection[k] = true;
                        count++;
                    }
                }
            }
        }
    }

    private boolean valid(int x, int y) {
        return x < board.length && x >= 0 && y < board[0].length && y >= 0;
    }

}
