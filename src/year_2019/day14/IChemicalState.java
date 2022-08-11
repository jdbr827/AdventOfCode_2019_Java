package year_2019.day14;

import java.util.Collection;

public interface IChemicalState {

    public Collection<String> knownChemicals();
    public void setChemicalAmount(String chemical, Long units);
    public Long getAmountAvailableOfChemical(String chemical);
    public void createChemical(String chemical, Long units);
    public void destroyChemical(String chemical, Long units);
}
