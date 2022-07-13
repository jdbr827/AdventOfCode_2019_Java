package year_2019.day10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10FileReader {
    public static java.util.List<Point> readInSpace(String fileName) throws IOException {
        java.util.List<Point> space = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int x = 0;
        while ((line = br.readLine()) != null) {
            for (int y = 0; y < line.length(); y++) {
                if (line.charAt(y) == '#') {
                    space.add(new Point(y, x));
                }
            }
            x++;
        }
        return space;
    }
}
