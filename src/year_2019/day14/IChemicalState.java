package year_2019.day14;

import java.util.Collection;

public interface IChemicalState {

    public Collection<String> knownChemicals();
    public Long getAmountAvailableOfChemical(String chemical);
    public void createChemical(String chemical, Long units);
    public void applyReactionToCreateChemical(String chemical, Long timesRun);
    public void applyReactionToDestroyChemical(String chemical, Long timesRun);
}
