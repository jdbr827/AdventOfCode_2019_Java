package year_2022.day_02;

import org.testng.internal.collections.Pair;
import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class Day2 {
    static final Pattern strategyLinePattern = Pattern.compile("([ABC]) ([XYZ])");

    static Map<String, RockPaperScissors> map1 = Map.ofEntries(
            entry("A", RockPaperScissors.ROCK),
            entry("B", RockPaperScissors.PAPER),
            entry("C", RockPaperScissors.SCISSORS),
            entry("X", RockPaperScissors.ROCK),
            entry("Y", RockPaperScissors.PAPER),
            entry("Z", RockPaperScissors.SCISSORS)
    );

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



    public static int part1(String fileName) throws FileNotFoundException {
        Day2Scanner scanner = new Day2Scanner(fileName);

        int totalScore = 0;
        Pair<RockPaperScissors, RockPaperScissors> pr;
        while ((pr = scanner.getNextOpponentAndStrategyPair()) != null){
            RockPaperScissors theyPlay = pr.first();
            RockPaperScissors strategy = pr.second();

            int thisScore = strategy.soloScore() + strategy.scoreVersus(theyPlay);
            totalScore += thisScore;
            //System.out.println(theyPlay.name() + " versus " + strategy.name() + " => " + thisScore);
        }
        return totalScore;
    }

     public static int part2(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        int totalScore = 0;
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Matcher m = strategyLinePattern.matcher(data);
            ReadIn.findOrElseThrow(m, "Could not read in RPS Strategy info");
            RockPaperScissors theyPlay = map2a.get(m.group(1));
            RPSResult goal = map2b.get(m.group(2));
            System.out.println(theyPlay +  "   " +  goal);
            RockPaperScissors strategy = theyPlay.toGetResult(goal);
            int thisScore = strategy.soloScore() + strategy.scoreVersus(theyPlay);
            System.out.println("They play " + theyPlay.name() + " We want " + goal.name() + " so we play " + strategy.name() + " => " + thisScore);
            totalScore += thisScore;
            //System.out.println(theyPlay.name() + " versus " + strategy.name() + " => " + thisScore);

        }
        return totalScore;
    }
}

