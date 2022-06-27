package year_2019.day24;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GameOfBugLife {
    private boolean[][] board;

    GameOfBugLife(boolean[][] board) {
        this.board = board;
        System.out.println(Arrays.deepToString(this.board));
    }

    /**
     * Returns true iff new board is same as old board
     *
     * @return
     */
    public boolean runOneMinute() {
        boolean[][] newBoard = new boolean[5][5];
        boolean isSame = true;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                newBoard[x][y] = determineNewState(x, y);
                if (newBoard[x][y] != board[x][y]) {
                    isSame = false;
                }
            }
        }
        this.board = newBoard;
        return isSame;
    }


    public static void main(String[] args) throws IOException {
        GameOfBugLife game = readInBoard();
        System.out.println(game.runOneMinute());
        System.out.println(Arrays.deepToString(game.board));
    }

    static GameOfBugLife readInBoard() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/year_2019/day24/day24input.txt"));
        boolean[][] board = new boolean[5][5];
        String line;
        for (int x = 0; x < 5; x++) {
            line = br.readLine();
            for (int y = 0; y < 5; y++) {
                char c = line.charAt(y);
                System.out.println(x + " " + y + " " + c);
                board[x][y] = (c == '#');
            }
        }
        return new GameOfBugLife(board);
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
}
