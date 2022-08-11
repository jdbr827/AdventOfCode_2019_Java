package year_2019.day14;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ChemicalState implements IChemicalState {
    final Map<String, Long> currentState = new HashMap<>();


    @Override
    public Collection<String> knownChemicals() {
        return currentState.keySet();
    }

    @Override
    public void setChemicalAmount(String chemical, Long units) {
        currentState.put(chemical, units);

    }

    @Override
    public Long getAmountAvailableOfChemical(String chemical) {
        return currentState.getOrDefault(chemical, 0L);
    }

    @Override
    public void createChemical(String chemical, Long units) {
        currentState.put(chemical, getAmountAvailableOfChemical(chemical) + units);
    }

    @Override
    public void destroyChemical(String chemical, Long units) {
        currentState.put(chemical, getAmountAvailableOfChemical(chemical) - units);
    }
}
