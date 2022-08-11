package year_2019.day14;

import java.util.Collection;
import java.util.Optional;

public interface IChemicalState {

    public Collection<String> knownChemicals();
    public Long getAmountAvailableOfChemical(String chemical);
    public void applyReactionsToDestroyChemical(Reaction reaction);

}
