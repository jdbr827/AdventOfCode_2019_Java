package year_2019.day14;

import java.io.IOException;

public class Day14 {


    public static Integer part1(String fileName) throws IOException {
        ReactionInfo reactionInfo = new ReactionInfo(fileName);
        return reactionInfo.findLeastRequiredOreForOneFuel();
    }
}
