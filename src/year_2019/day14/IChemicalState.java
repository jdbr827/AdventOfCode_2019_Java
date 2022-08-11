package year_2019.day14;

import java.util.Collection;
import java.util.Optional;

public interface IChemicalState {

    Collection<String> knownChemicals();
    Long getAmountAvailableOfChemical(String chemical);
    void applyReactionsToDestroyChemical(Reaction reaction);


    default Optional<String> findUnDestroyedChemical() {
         return knownChemicals().stream()
                 .filter((chem) -> !(chem.equals("ORE")) && getAmountAvailableOfChemical(chem) > 0)
                 .findAny();
     }

    default void balanceChemicalState(IReactionInfo reactionInfo) {
        Optional<String> unDestroyedChemical;
        while ((unDestroyedChemical = findUnDestroyedChemical()).isPresent()) {
            applyReactionsToDestroyChemical(reactionInfo.getReactionForChemical(unDestroyedChemical.get()));
        }
    }
}
