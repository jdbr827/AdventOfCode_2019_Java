package year_2019.day14;

import java.util.Collection;
import java.util.Optional;

public interface IChemicalState {

    public Collection<String> knownChemicals();
    public Long getAmountAvailableOfChemical(String chemical);
    public void applyReactionsToDestroyChemical(Reaction reaction);


     default Optional<String> findUnbalancedChemical() {
         return knownChemicals().stream()
                 .filter((chem) -> !(chem.equals("ORE")) && getAmountAvailableOfChemical(chem) > 0)
                 .findAny();
     }

}
