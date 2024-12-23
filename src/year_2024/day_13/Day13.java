package year_2024.day_13;

import lombok.AllArgsConstructor;
import org.testng.internal.collections.Pair;
import utils.AOCScanner;
import utils.ReadIn;


import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day13 {

    @AllArgsConstructor
    static class ClawMachine {
        Pair<Long, Long> aButton;
        Pair<Long, Long> bButton;
        Pair<Long, Long> prizeLocation;



        public long fewestTokensToWinPrize() {


            BigInteger[] bPresses = BigInteger.valueOf(aButton.first() * prizeLocation.second() - prizeLocation.first() * aButton.second())
                    .divideAndRemainder(BigInteger.valueOf(aButton.first()*bButton.second()- bButton.first()*aButton.second()));
            //System.out.println(Arrays.toString(bPresses));

            if (bPresses[1].longValue() != 0) {
                return 0;
            }

            BigInteger[] aPresses = BigInteger.valueOf(prizeLocation.first() - (bPresses[0].longValue()*bButton.first())).divideAndRemainder(BigInteger.valueOf(aButton.first()));

            if (aPresses[1].longValue() != 0) {
                return 0;
            }

            return 3*aPresses[0].longValue() + bPresses[0].longValue();
        }


    }

    class Day13Scanner extends AOCScanner {
        static final Pattern clawPattern = Pattern.compile(
                "Button A: X\\+(\\d*), Y\\+(\\d*)\n" +
                        "Button B: X\\+(\\d*), Y\\+(\\d*)\n" +
                        "Prize: X=(\\d*), Y=(\\d*)");

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
                    Pair.of(Long.parseLong(m.group(1)), Long.parseLong(m.group(2))),
                    Pair.of(Long.parseLong(m.group(3)), Long.parseLong(m.group(4))),
                    Pair.of(Long.parseLong(m.group(5)), Long.parseLong(m.group(6)))));
        }
    }

    List<ClawMachine> clawMachines = new LinkedList<>();

    public Day13(String inputFilename) {
        new Day13Scanner(inputFilename).scan();
    }

    public long fewestTokensToWinAllPossiblePrizes() {
        return clawMachines.stream().map(ClawMachine::fewestTokensToWinPrize).reduce(0L, Math::addExact);
    }

    public long fewestTokensToWinAllPossiblePrizesFar() {
        for (ClawMachine clawMachine : clawMachines) {
            clawMachine.prizeLocation = Pair.of(
                    clawMachine.prizeLocation.first() + 10000000000000L,
                    clawMachine.prizeLocation.second() + 10000000000000L
            );
        }
        return fewestTokensToWinAllPossiblePrizes();
    }
}
