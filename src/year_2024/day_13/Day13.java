package year_2024.day_13;

import lombok.AllArgsConstructor;
import utils.AOCScanner;
import utils.ReadIn;

import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day13 {

    @AllArgsConstructor
    static class ClawMachine {
        Point aPress;
        Point bPress;
        Point prizeLocation;
    }

    static class Day13Scanner extends AOCScanner {
        static final Pattern clawPattern = Pattern.compile(
                "Button A: X\\+([\\d]*), Y\\+([\\d]*)[\\s]*" +
                        "Button B: X\\+([\\d]*), Y\\+([\\d]*)\n" +
                        "Prize: X=([\\d]*), Y=([\\d]*)");

        public Day13Scanner(String fileName) {
            super(fileName);
        }

        public void scan() {
            while (scanner.hasNextLine()) {
                scanNextClawMachine();
            }
        }

        public void scanNextClawMachine() {
            String data = scanner.nextLine();
            data += "\n" + scanner.nextLine();
            data += "\n" + scanner.nextLine();
            Matcher m = clawPattern.matcher(data);
            ReadIn.findOrElseThrow(m, "Could not understand matcher data " + data);
            System.out.println(m.groupCount());
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // blank line;
            }
        }
    }

    List<ClawMachine> clawMachines;

    public Day13(String inputFilename) {
        new Day13Scanner(inputFilename).scan();
    }

    public int fewestTokensToWinAllPossiblePrizes() {
        return 0;
    }
}
