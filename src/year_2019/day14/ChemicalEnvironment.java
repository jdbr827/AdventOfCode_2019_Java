package year_2019.day14;

import utils.BinarySearchUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ChemicalEnvironment {
    IReactionInfo reactionInfo;


    ChemicalEnvironment(IReactionInfo reactionInfo) {
        this.reactionInfo = reactionInfo;
    }


    public long fuelYouCanMakeWithNOre(long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastRequiredOreForNFuel, availableOre);
    }

    public long leastRequiredOreForNFuel(long N) {

        class StoichDoer {
            final Map<String, Long> currentState = new HashMap<>();

            StoichDoer(long desiredFuel) {
                currentState.put("FUEL", desiredFuel);
                balance();
            }

            long getRequiredOre() {
                return currentState.getOrDefault("ORE", 0L);
            }


            private void balance() {
                while (isNotBalanced()) {
                }
            }

            private void applyReactionsToCreate(String chemical) {
                Long currentQuantityOfChemical = currentState.getOrDefault(chemical, 0L);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                Long timesRun = Math.floorDiv(currentQuantityOfChemical, reactionQuantityOfChemical);

                currentState.put(chemical, currentQuantityOfChemical + (timesRun * reactionQuantityOfChemical));
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0L) - (timesRun * inputChemicals.get(inputChemical)));
                }
            }

            private void applyReactionsToDestroy(String chemical) {
                Long currentQuantityOfChemical = getCurrentQuantityOfChemical(chemical);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                Long timesRun = (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);

                currentState.put(chemical, currentQuantityOfChemical - (timesRun * reactionQuantityOfChemical));
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    currentState.put(inputChemical, getCurrentQuantityOfChemical(inputChemical) + (timesRun * inputChemicals.get(inputChemical)));
                }
            }

            private Long getCurrentQuantityOfChemical(String chemical) {
                return currentState.getOrDefault(chemical, 0L);
            }

            private boolean isNotBalanced() {
                for (String chemical : currentState.keySet()) {
                    if (chemical.equals("ORE")) {
                        continue;
                    }
                    if (-1L * reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical) > currentState.get(chemical)) {
                        while (-1L * reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical) > currentState.get(chemical)) {
                            applyReactionsToCreate(chemical);
                        }
                        return true;
                    }
                    if (currentState.get(chemical) > 0) {
                        while (currentState.get(chemical) > 0) {
                            applyReactionsToDestroy(chemical);
                        }
                        return true;
                    }
                }
                return false;


            }
        }
        return new StoichDoer(N).getRequiredOre();
    }

}


