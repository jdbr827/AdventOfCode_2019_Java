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

    static Map<String, RockPaperScissors> map = Map.ofEntries(
            entry("A", RockPaperScissors.ROCK),
            entry("B", RockPaperScissors.PAPER),
            entry("C", RockPaperScissors.SCISSORS),
            entry("X", RockPaperScissors.ROCK),
            entry("Y", RockPaperScissors.PAPER),
            entry("Z", RockPaperScissors.SCISSORS)
    );


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/year_2022/day_2/day_2_input.txt");
        Scanner scanner = new Scanner(file);

        int totalScore = 0;
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Matcher m = reactionInfoPattern.matcher(data);
            ReadIn.findOrElseThrow(m, "Could not read in RPS Strategy info");
            RockPaperScissors theyPlay = map.get(m.group(1));
            RockPaperScissors strategy = map.get(m.group(2));
            int thisScore = strategy.soloScore() + strategy.scoreVersus(theyPlay);
            totalScore += thisScore;
            System.out.println(theyPlay.name() + " versus " + strategy.name() + " => " + thisScore);

        }
        System.out.println(totalScore);

    }
}

