package year_2019.day10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static com.google.common.math.IntMath.gcd;


public class Day10 {

    public static int part1(String fileName) throws IOException {
        Space space = new Space(fileName);
        return space.findMostVisibleNeighbors();
    }

    public static Point findBestStation(String fileName) throws IOException {
        Space space = new Space(fileName);
        return space.findBestMonitoringStation();
    }
}


