package year_2019.day14;

import java.util.Collection;

public interface IChemicalState {

    public Collection<String> knownChemicals();
    public Long getAmountAvailableOfChemical(String chemical);
    public void applyReactionToDestroyChemical(Reaction reaction, Long timesRun);
}
