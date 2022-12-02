package year_2022.day_2;

import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class Day2 {
    static final Pattern reactionInfoPattern = Pattern.compile("([ABC]) ([XYZ])");

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




    public static void main(String[] args) throws FileNotFoundException {
        //part1();
        part2();

    }

    private static void part1() throws FileNotFoundException {
        File file = new File("src/year_2022/day_2/day_2_input.txt");
        Scanner scanner = new Scanner(file);

        int totalScore = 0;
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Matcher m = reactionInfoPattern.matcher(data);
            ReadIn.findOrElseThrow(m, "Could not read in RPS Strategy info");
            RockPaperScissors theyPlay = map1.get(m.group(1));
            RockPaperScissors strategy = map1.get(m.group(2));
            int thisScore = strategy.soloScore() + strategy.scoreVersus(theyPlay);
            totalScore += thisScore;
            System.out.println(theyPlay.name() + " versus " + strategy.name() + " => " + thisScore);

        }
        System.out.println(totalScore);
    }

     private static void part2() throws FileNotFoundException {
        File file = new File("src/year_2022/day_2/day_2_input.txt");
        Scanner scanner = new Scanner(file);

        int totalScore = 0;
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Matcher m = reactionInfoPattern.matcher(data);
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
        System.out.println(totalScore);
    }
}

