package year_2022.day_15;

import utils.AOCScanner;
import utils.ReadIn;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15Scanner extends AOCScanner {
    public Day15Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    static final Pattern sensorPattern = Pattern.compile("Sensor at x=(-?[0-9]+), y=(-?[0-9]+): closest beacon is at x=(-?[0-9]+), y=(-?[0-9]+)");

    Map<Point, Point> sensorInfo = new HashMap<>();

    public void getNextLine() {
        if (scanner.hasNextLine()) {
            Matcher m = sensorPattern.matcher(scanner.nextLine());
            ReadIn.findOrElseThrow(m, "Could not match ranges pattern");

            sensorInfo.put(
                    new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))),
                    new Point(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)))
            );

        }
    }

    public Map<Point, Point> readInSensorInfo() {
        while (scanner.hasNextLine()) {
            getNextLine();
        }
        return sensorInfo;
    }
}
