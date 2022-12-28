package year_2022.day_12;

import java.awt.*;
import java.util.List;

public class Day12Matrix extends Matrix2D<Character> {

    public Day12Matrix(List<List<Character>> matrix) {
        super(matrix);
    }

    int getRelativeHeight(int x, int y) {
        return (int) getRelativeHeight(getElevation(x, y));
    }

    Character getElevation(int x, int y) {
        return getValue(x, y);
    }



    static char getRelativeHeight(Character c) {
        switch (c) {
            case 'S':
                return 'a';
            case 'E':
                return 'z';
            default:
                return c;
        }
    }

    int getRelativeHeight(Point p) {
        return getRelativeHeight(p.x, p.y);
    }

}
