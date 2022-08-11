package year_2019.day14;

import java.util.Collection;
import java.util.Optional;

public interface IChemicalState {

    Collection<String> knownChemicals();
    Long getAmountAvailableOfChemical(String chemical);
    void applyReactionsToDestroyChemical(Reaction reaction);


     default Optional<String> findUnbalancedChemical() {
         return knownChemicals().stream()
                 .filter((chem) -> !(chem.equals("ORE")) && getAmountAvailableOfChemical(chem) > 0)
                 .findAny();
     }

}
