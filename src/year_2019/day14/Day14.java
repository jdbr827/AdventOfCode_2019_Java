package year_2019.day14;

import lombok.AllArgsConstructor;
import utils.BinarySearchUtil;

import java.io.IOException;

@AllArgsConstructor
public class Day14 {

    private IReactionInfo reactionInfo;

    public static Day14 fromReactionsFileName(String reactionsFileName) throws IOException {
        return new Day14(IReactionInfo.create(reactionsFileName));
    }


    /* Part 1 */
    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {
        IChemicalState chemicalState = IChemicalState.createWithStartingFuel(desiredFuel);
        chemicalState.balanceChemicalState(reactionInfo);
        return chemicalState.getAmountAvailableOfChemical("ORE");
    }

    /* Part 2 */
    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
