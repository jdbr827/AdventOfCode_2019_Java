package year_2019.day14;

import java.io.IOException;

public interface IReactionInfo {
    Reaction getReactionForChemical(String chemical);

    static IReactionInfo create(String reactionsFileName) throws IOException {
        return new ReactionInfo(reactionsFileName);

    }
}
