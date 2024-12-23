package year_2024.day_13;

import lombok.AllArgsConstructor;
import utils.AOCScanner;
import utils.ReadIn;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day13 {

    @AllArgsConstructor
    static class ClawMachine {
        Point aButton;
        Point bButton;
        Point prizeLocation;

        public int fewestTokensToWinPrize() {
            double bPresses = (double) (aButton.x * prizeLocation.y - prizeLocation.x * aButton.y) / (aButton.x*bButton.y- bButton.x*aButton.y);
            double aPresses = (prizeLocation.x - (bPresses*bButton.x))/aButton.x;

            if ((int) aPresses == aPresses && (int) bPresses == bPresses) {
                return (3 * (int)aPresses) + (int) bPresses;
            }
            return 0;
        }
    }

    class Day13Scanner extends AOCScanner {
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
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // blank line;
            }
            clawMachines.add(new ClawMachine(
                    new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))),
                    new Point(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))),
                    new Point(Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)))));
        }
    }

    List<ClawMachine> clawMachines = new LinkedList<>();

    public Day13(String inputFilename) {
        new Day13Scanner(inputFilename).scan();
    }

    public int fewestTokensToWinAllPossiblePrizes() {
        return clawMachines.stream().map(ClawMachine::fewestTokensToWinPrize).reduce(0, Math::addExact);
    }
}
