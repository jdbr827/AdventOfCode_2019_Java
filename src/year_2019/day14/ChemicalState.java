package year_2019.day14;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ChemicalState implements IChemicalState {
    final Map<String, Long> currentState = new HashMap<>();
    IReactionInfo reactionInfo;

    public ChemicalState(Long desiredFuel, IReactionInfo reactionInfo) {
        this.reactionInfo = reactionInfo;
        currentState.put("FUEL", desiredFuel);
    }


    @Override
    public Collection<String> knownChemicals() {
        return currentState.keySet();
    }

    @Override
    public Long getAmountAvailableOfChemical(String chemical) {
        return currentState.getOrDefault(chemical, 0L);
    }

    private void createChemical(String chemical, Long units) {
        currentState.put(chemical, getAmountAvailableOfChemical(chemical) + units);
    }

    private void destroyChemical(String chemical, Long units) {
        currentState.put(chemical, getAmountAvailableOfChemical(chemical) - units);
    }


    @Override
    public void applyReactionToDestroyChemical(String chemical, Long timesRun) {
        destroyChemical(chemical, timesRun * reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical));
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    createChemical(inputChemical, timesRun * inputChemicals.get(inputChemical));
                }

    }
}
