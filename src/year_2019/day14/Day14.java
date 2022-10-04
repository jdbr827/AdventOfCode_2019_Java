package year_2019.day14;

import lombok.AllArgsConstructor;
import utils.BinarySearchUtil;

import java.io.IOException;

@AllArgsConstructor
public class Day14 implements IDay14 {

    private IReactionInfo reactionInfo;


    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {
        IChemicalState chemicalState = IChemicalState.createWithStartingFuel(desiredFuel);
        chemicalState.balanceChemicalState(reactionInfo);
        return chemicalState.getAmountAvailableOfChemical("ORE");
    }

    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
