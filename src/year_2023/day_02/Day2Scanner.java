package year_2023.day_02;

import utils.AOCScanner;
import utils.AOCScanner_2023;

import java.util.ArrayList;
import java.util.List;

public class Day2Scanner {


    public static Day2 scan(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        List<Day2GameInfo> gameInfos = new ArrayList<>();
        scanner.forEachLine((line) -> gameInfos.add(parseLine(line)));
        return new Day2(gameInfos);
    }

    static Day2GameInfo parseLine(String line) {
        String[] parsedLine1 = line.split(": ");

        int gameId = Integer.parseInt(parsedLine1[0].split(" ")[1]);


        List<Day2BagPull> pulls = new ArrayList<>();


        String[] parsedPulls = parsedLine1[1].split("; ");

        for (String parsedPull : parsedPulls) {
            int red = 0; int green = 0; int blue = 0;
            String[] parsedPullColors = parsedPull.split(", ");
            for (String parsedPullColor : parsedPullColors) {
                String[] parsedPullColorNums = parsedPullColor.split(" ");
                String color = parsedPullColorNums[1];
                int num = Integer.parseInt(parsedPullColorNums[0]);
                switch (color) {
                    case "red":
                        red = num;
                        break;
                    case "green":
                        green = num;
                        break;
                    case "blue":
                        blue = num;
                        break;
                }
            }
            Day2BagPull pull = new Day2BagPull(red, green, blue);
            pulls.add(pull);
        }

        return new Day2GameInfo(gameId, pulls);
    }


}
