package year_2024.day_17;

import lombok.AllArgsConstructor;
import utils.AOCScanner;
import utils.ReadIn;
import year_2024.day_14.Day14;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Day17 {
    int registerA;
    int registerB;
    int registerC;
    List<Integer> program;

    public static Day17 fromFile(String inputFilename) {
        return new Day17Scanner(inputFilename).scan();
    }

    public String getOutputAfterRunning() {
        System.out.println(program);
        return null;
    }
}
