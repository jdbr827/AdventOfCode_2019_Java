package year_2019.day14;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import utils.BinarySearchUtil;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class Day14 implements IDay14 {

    IReactionInfo reactionInfo;


    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {

        class StoichDoer {
            final IChemicalState chemicalState = new ChemicalState(desiredFuel);

            StoichDoer() {
                balance();
            }

            long getRequiredOre() {
                return chemicalState.getAmountAvailableOfChemical("ORE");
            }


            private void balance() {
                while (isNotBalanced()) {
                }
            }

            @NotNull
            private Long numTimesYouCanDestroy(String chemical) {
                Long currentQuantityOfChemical = chemicalState.getAmountAvailableOfChemical(chemical);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                return (Long) (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);
            }

            private boolean isNotBalanced() {
                for (String chemical : chemicalState.knownChemicals()) {
                    if (chemical.equals("ORE")) {
                        continue;
                    }
                    if (chemicalState.getAmountAvailableOfChemical(chemical) > 0) {
                        Reaction reaction = reactionInfo.getReaction(chemical);
                        Long numTimes = numTimesYouCanDestroy(chemical);
                        chemicalState.applyReactionToDestroyChemical(reaction, numTimes);
                        return true;
                    }
                }
                return false;


            }

        }
        return new StoichDoer().getRequiredOre();
    }

    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
