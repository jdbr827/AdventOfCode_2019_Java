package year_2019.day14;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import utils.BinarySearchUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class Day14 implements IDay14 {

    IReactionInfo reactionInfo;


    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {

        class StoichDoer {
            final IChemicalState chemicalState = new ChemicalState();

            StoichDoer(long desiredFuel1) {
                createChemicalAmount("FUEL", desiredFuel1);
                balance();
            }

            long getRequiredOre() {
                return getCurrentQuantityOfChemical("ORE");
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
                return chemicalState.getAmountAvailableOfChemical(chemical);
            }

            private void setChemicalAmount(String chemical, Long amount) {
                chemicalState.setChemicalAmount(chemical, amount);
            }

            private void createChemicalAmount(String chemical, Long amountCreated) {
                setChemicalAmount(chemical, getCurrentQuantityOfChemical(chemical) + amountCreated);
            }

            private void destroyChemicalAmount(String chemical, Long amountDestroyed) {
                setChemicalAmount(chemical, getCurrentQuantityOfChemical(chemical) - amountDestroyed);
            }

            private boolean isNotBalanced() {
                for (String chemical : getKnownChemicals()) {
                    if (chemical.equals("ORE")) {
                        continue;
                    }
                    if (-1L * reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical) > getCurrentQuantityOfChemical(chemical)) {
                        while (-1L * reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical) > getCurrentQuantityOfChemical(chemical)) {
                            applyReactionsToCreate(chemical);
                        }
                        return true;
                    }
                    if (getCurrentQuantityOfChemical(chemical) > 0) {
                        while (getCurrentQuantityOfChemical(chemical) > 0) {
                            applyReactionsToDestroy(chemical);
                        }
                        return true;
                    }
                }
                return false;


            }

            @NotNull
            private Collection<String> getKnownChemicals() {
                return chemicalState.knownChemicals();
            }
        }
        return new StoichDoer(desiredFuel).getRequiredOre();
    }

    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
