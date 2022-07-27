package year_2019.day14;

import java.io.IOException;

public class Day14 {

    public static Long part1(String fileName) throws IOException {
        ReactionInfo reactionInfo = new ReactionInfo(fileName);
        return reactionInfo.leastRequiredOreForOneFuel();
    }

    public static Long part2(String fileName) throws IOException {
        ReactionInfo reactionInfo = new ReactionInfo(fileName);
        return reactionInfo.fuelYouCanMakeWithNOre();
    }
}
