package year_2019.day14;

import java.util.Map;

public interface IReactionInfo {
    Integer getQuantityOfChemicalMadeByOneReaction(String chemical);
    Map<String, Integer> getInputsForChemical(String chemical);
}
