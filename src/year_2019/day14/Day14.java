package year_2019.day14;

import lombok.AllArgsConstructor;
import utils.BinarySearchUtil;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
public class Day14 implements IDay14 {

    IReactionInfo reactionInfo;


    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {
        IChemicalState chemicalState = new ChemicalState(desiredFuel);
        balanceChemicalState(chemicalState);
        return chemicalState.getAmountAvailableOfChemical("ORE");
    }

    private void balanceChemicalState(IChemicalState chemicalState) {
        Optional<String> unbalancedChemical;
        while ((unbalancedChemical = chemicalState.findUnbalancedChemical()).isPresent()) {
            chemicalState.applyReactionsToDestroyChemical(reactionInfo.getReaction(unbalancedChemical.get()));
        }
    }

    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
