package year_2019.day10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Day10 {
    public static void main(String[] args) throws IOException {
        List<Point> asteroids = readInSpace();
    }

    private static List<Point> readInSpace() throws IOException {
        String fileName = "src/year_2019/day10/day_10_input_1.txt";
        List<Point> space = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int x = 0;
        while ((line = br.readLine()) != null) {
            for (int y=0; y<line.length(); y++) {
                if (line.charAt(y) == '#') {
                    space.add(new Point(x, y));
                }
            }
            x++;
        }
        return space;
    }
}


class Space {

}