package year_2019.day14;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ChemicalState implements IChemicalState {
    final Map<String, Long> currentState = new HashMap<>();

    public ChemicalState(Long desiredFuel) {
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

    private Long numTimesYouCanApplyReaction(Reaction reaction) {
                Long currentQuantityOfChemical = getAmountAvailableOfChemical(reaction.outputChemical);
                Integer reactionQuantityOfChemical = reaction.outputChemicalQuantity;
                return (Long) (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);
    }


    @Override
    public void applyReactionsToDestroyChemical(Reaction reaction) {
        Long timesToRun = numTimesYouCanApplyReaction(reaction);
        destroyChemical(reaction.outputChemical, timesToRun * reaction.outputChemicalQuantity);
        reaction.inputChemicalInfo
                .forEach((inputChem, reactionAmt) ->
                        createChemical(inputChem, timesToRun * reactionAmt)
                );


    }
}
