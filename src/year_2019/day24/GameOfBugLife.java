package year_2019.day24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GameOfBugLife {
    private Boolean[][] board;
    private boolean[] previousStates = new boolean[(int) Math.pow(2, 25.0)];

    GameOfBugLife(Boolean[][] board) {
        this.board = board;
        System.out.println(Arrays.deepToString(this.board));
    }

    /**
     * Returns true iff new board is same as old board
     *
     * @return
     */
    public void runOneMinute() {
        Boolean[][] newBoard = new Boolean[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                newBoard[x][y] = determineNewState(x, y);
            }
        }
        this.board = newBoard;
    }


    public static void main(String[] args) throws IOException {
        GameOfBugLife game = readInBoardToSingleGame();
        System.out.println(game.runUntilRepeat());
        System.out.println(Arrays.deepToString(game.board));
    }

    private int runUntilRepeat() {
        int bdr;
        while ((bdr = this.acknowledgeCurrentBiodiversityRating()) == -1) {
            this.runOneMinute();
        }
        return bdr;
    }

    private int acknowledgeCurrentBiodiversityRating() {
        int bdr = this.getCurrentBiodiversityRating();
        if (this.previousStates[bdr]) {
            return bdr;
        } else {
            this.previousStates[bdr] = true;
            return -1;
        }
    }

    static GameOfBugLife readInBoardToSingleGame() throws IOException {
        return new GameOfBugLife(readInBoard());
    }

    static Boolean[][] readInBoard() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/year_2019/day24/day24input.txt"));
        Boolean[][] board = new Boolean[5][5];
        String line;
        for (int x = 0; x < 5; x++) {
            line = br.readLine();
            for (int y = 0; y < 5; y++) {
                char c = line.charAt(y);
                board[x][y] = (c == '#');
            }
        }
        return board;
    }


    private boolean determineNewState(int x, int y) {
        boolean currentState = this.board[x][y];
        int liveNeighbors = determineLiveNeighbors(x, y);
        if (currentState) {
            return liveNeighbors == 1;
        } else {
            return liveNeighbors == 1 || liveNeighbors == 2;
        }
    }

    private int determineLiveNeighbors(int x, int y) {
        int liveNeighbors = 0;
        if (x > 0) {
            liveNeighbors += this.board[x-1][y] ? 1 : 0;
        }
        if (x < 4) {
            liveNeighbors += this.board[x + 1][y] ? 1 : 0;
        }
        if (y > 0) {
            liveNeighbors += this.board[x][y-1] ? 1 : 0;
        }
        if (y < 4) {
            liveNeighbors += this.board[x][y + 1] ? 1 : 0;
        }
        return liveNeighbors;
    }

    private int getCurrentBiodiversityRating() {
        int powerOf2 = 0;
        int tot = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (this.board[x][y]) {
                    tot += Math.pow(2, powerOf2);
                }
                powerOf2++;
            }
        }
        return tot;
    }
}
