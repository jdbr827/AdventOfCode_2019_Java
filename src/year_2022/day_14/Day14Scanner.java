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

    void readInNextRockLine() {
        if (scanner.hasNextLine()) {
            List<JavaPoint> cornerRocks = Arrays.stream(scanner.nextLine().split(" -> ")).map(
                    this::createJavaPointFromString).collect(Collectors.toList());
            System.out.println(cornerRocks);
        }

    }

    private JavaPoint createJavaPointFromString(String s) {
        String[] individualStrs = s.split(",");
        return new JavaPoint(Integer.parseInt(individualStrs[0]), Integer.parseInt(individualStrs[1]));
    }

    public static void main(String[] args) {
        Day14Scanner scanner = new Day14Scanner("src/year_2022/day_14/test/day_14_sample_input.txt");
        scanner.readInNextRockLine();
        scanner.readInNextRockLine();
    }
}
