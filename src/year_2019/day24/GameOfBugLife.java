package year_2019.day24;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GameOfBugLife {
    private int[][] board;

    GameOfBugLife(int[][] board){
        this.board = board;
        System.out.println(Arrays.deepToString(this.board));
    }





    public static void main(String[] args) throws IOException {
        GameOfBugLife game = readInBoard();
    }
    static GameOfBugLife readInBoard() throws IOException {
        FileReader inputFileReader = new FileReader("./src/year_2019/day24/day24input.txt");
        int[][] board = new int[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                char c = (char) inputFileReader.read();
                System.out.println(x + " " + y + " " + c);
                board[x][y] = (c == '#')? 1 : 0;
            }
            inputFileReader.read(); // newline;
        }
        return new GameOfBugLife(board);
    }


}
