package year_2022.day_02;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;
import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class Day2Scanner extends AOCScanner  {
    static final Pattern strategyLinePattern = Pattern.compile("([ABC]) ([XYZ])");

    Day2Scanner(String fileName) throws FileNotFoundException {
       super(fileName);
    }

    private Matcher getNextPair() {
        if (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Matcher m = strategyLinePattern.matcher(data);
            ReadIn.findOrElseThrow(m, "Could not read in RPS Strategy info");
            return m;
        }
        return null;
    }

    static Map<String, RockPaperScissors> map1 = Map.ofEntries(
            entry("A", RockPaperScissors.ROCK),
            entry("B", RockPaperScissors.PAPER),
            entry("C", RockPaperScissors.SCISSORS),
            entry("X", RockPaperScissors.ROCK),
            entry("Y", RockPaperScissors.PAPER),
            entry("Z", RockPaperScissors.SCISSORS)
    );

    public Pair<RockPaperScissors, RockPaperScissors> getNextOpponentAndStrategyPair() {
        Matcher m = getNextPair();
        if (m == null) {return null;}
        RockPaperScissors theyPlay = map1.get(m.group(1));
        RockPaperScissors strategy = map1.get(m.group(2));
        return new Pair<>(theyPlay, strategy);
    }

    static Map<String, RockPaperScissors> map2a = Map.ofEntries(
            entry("A", RockPaperScissors.ROCK),
            entry("B", RockPaperScissors.PAPER),
            entry("C", RockPaperScissors.SCISSORS)
    );

       static Map<String, RPSResult> map2b = Map.ofEntries(
               entry("X", RPSResult.LOSS),
               entry("Y", RPSResult.DRAW),
               entry("Z", RPSResult.WIN)
       );

    public Pair<RockPaperScissors, RPSResult> getNextOpponentAndResultPair() {
        Matcher m = getNextPair();
        if (m == null) {return null;}
        RockPaperScissors theyPlay = map2a.get(m.group(1));
        RPSResult result = map2b.get(m.group(2));
        return new Pair<>(theyPlay, result);
    }

}
