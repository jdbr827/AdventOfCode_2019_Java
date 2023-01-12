package year_2022.day_14;

import utils.AOCScanner;
import viewModelUtil.JavaPoint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day14Scanner extends AOCScanner {
    public Day14Scanner(String fileName) {
        super(fileName);
    }

    Set<JavaPoint> rockSet = new HashSet<JavaPoint>();

    private List<JavaPoint> readInNextCornerRockChain() {
        return Arrays.stream(scanner.nextLine().split(" -> "))
                .map(Day14Scanner::createJavaPointFromString)
                .collect(Collectors.toList());
    }

    private void addRockLine(JavaPoint lastRock, JavaPoint thisRock) {
        if (lastRock.x == thisRock.x) {
            for (int y = Math.min(lastRock.y, thisRock.y); y <= Math.max(lastRock.y, thisRock.y); y++) {
                rockSet.add(new JavaPoint(lastRock.x, y));
            }
        } else if (lastRock.y == thisRock.y) {
            for (int x = Math.min(lastRock.x, thisRock.x); x <= Math.max(lastRock.x, thisRock.x); x++) {
                rockSet.add(new JavaPoint(x, lastRock.y));
            }
        } else {
            throw new Error("consecutive corner rocks " + lastRock + " and " + thisRock + " not aligned to grid");
        }
    }

    public Set<JavaPoint> readInRocks() {
        while (scanner.hasNextLine()) {
            List<JavaPoint> cornerRocks = readInNextCornerRockChain();
            for (int i = 1; i < cornerRocks.size(); i++) {
                JavaPoint lastRock = cornerRocks.get(i - 1);
                JavaPoint thisRock = cornerRocks.get(i);
                addRockLine(lastRock, thisRock);
            }
        }
        return rockSet;
    }

    private static JavaPoint createJavaPointFromString(String s) {
        String[] individualStrs = s.split(",");
        return new JavaPoint(Integer.parseInt(individualStrs[0]), Integer.parseInt(individualStrs[1]));
    }

    public static void main(String[] args) {
        Day14Scanner scanner = new Day14Scanner("src/year_2022/day_14/test/day_14_sample_input.txt");
        scanner.readInNextCornerRockChain();
        scanner.readInNextCornerRockChain();
    }
}
