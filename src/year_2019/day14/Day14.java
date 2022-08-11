package year_2019.day14;

import lombok.AllArgsConstructor;
import utils.BinarySearchUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class Day14 implements IDay14 {

    IReactionInfo reactionInfo;


    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {

        class StoichDoer {
            final Map<String, Long> currentState = new HashMap<>();

            StoichDoer(long desiredFuel1) {
                currentState.put("FUEL", desiredFuel1);
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
                Long currentQuantityOfChemical = getCurrentQuantityOfChemical(chemical);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                Long timesRun = Math.floorDiv(currentQuantityOfChemical, reactionQuantityOfChemical);

                createChemicalAmount(chemical, timesRun * reactionQuantityOfChemical);
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    destroyChemicalAmount(inputChemical, timesRun * inputChemicals.get(inputChemical));
                }
            }

            private void applyReactionsToDestroy(String chemical) {
                Long currentQuantityOfChemical = getCurrentQuantityOfChemical(chemical);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                Long timesRun = (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);

                destroyChemicalAmount(chemical, timesRun * reactionQuantityOfChemical);
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    createChemicalAmount(inputChemical, timesRun * inputChemicals.get(inputChemical));
                }
            }

            private Long getCurrentQuantityOfChemical(String chemical) {
                return currentState.getOrDefault(chemical, 0L);
            }

            private void setChemicalAmount(String chemical, Long amount) {
                currentState.put(chemical, amount);
            }

            private void createChemicalAmount(String chemical, Long amountCreated) {
                setChemicalAmount(chemical, getCurrentQuantityOfChemical(chemical) + amountCreated);
            }

            private void destroyChemicalAmount(String chemical, Long amountDestroyed) {
                setChemicalAmount(chemical, getCurrentQuantityOfChemical(chemical) - amountDestroyed);
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
        return new StoichDoer(desiredFuel).getRequiredOre();
    }

    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
