package year_2019.day14;

import lombok.AllArgsConstructor;
import utils.BinarySearchUtil;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class Day14 implements IDay14 {

    IReactionInfo reactionInfo;


    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {

        class StoichDoer {
            final IChemicalState chemicalState = new ChemicalState();

            StoichDoer(long desiredFuel1) {
                chemicalState.createChemical("FUEL", desiredFuel1);
                balance();
            }

            long getRequiredOre() {
                return chemicalState.getAmountAvailableOfChemical("ORE");
            }


            private void balance() {
                while (isNotBalanced()) {
                }
            }

            private void applyReactionsToCreate(String chemical) {
                Long currentQuantityOfChemical = chemicalState.getAmountAvailableOfChemical(chemical);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                Long timesRun = Math.floorDiv(currentQuantityOfChemical, reactionQuantityOfChemical);

                chemicalState.createChemical(chemical, timesRun * reactionQuantityOfChemical);
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    chemicalState.destroyChemical(inputChemical, timesRun * inputChemicals.get(inputChemical));
                }
            }

            private void applyReactionsToDestroy(String chemical) {
                Long currentQuantityOfChemical = chemicalState.getAmountAvailableOfChemical(chemical);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                Long timesRun = (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);

                chemicalState.destroyChemical(chemical, timesRun * reactionQuantityOfChemical);
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    chemicalState.createChemical(inputChemical, timesRun * inputChemicals.get(inputChemical));
                }
            }

            private boolean isNotBalanced() {
                for (String chemical : chemicalState.knownChemicals()) {
                    if (chemical.equals("ORE")) {
                        continue;
                    }
                    if (-1L * reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical) > chemicalState.getAmountAvailableOfChemical(chemical)) {
                        while (-1L * reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical) > chemicalState.getAmountAvailableOfChemical(chemical)) {
                            applyReactionsToCreate(chemical);
                        }
                        return true;
                    }
                    if (chemicalState.getAmountAvailableOfChemical(chemical) > 0) {
                        while (chemicalState.getAmountAvailableOfChemical(chemical) > 0) {
                            applyReactionsToDestroy(chemical);
                        }
                        return true;
                    }
                }
                return false;


            }

        }
        return new StoichDoer(desiredFuel).getRequiredOre();
    }

    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
