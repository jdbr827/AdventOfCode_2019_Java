package year_2019.day14;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ChemicalEnvironment {
    IReactionInfo reactionInfo;
    private final static long ONE_TRILLION = 1000000000000L;

    ChemicalEnvironment(IReactionInfo reactionInfo) throws IOException {
        this.reactionInfo = reactionInfo;
    }


    public Long leastRequiredOreForOneFuel() throws IOException {
        return leastRequiredOreForNFuel(1L);
    }

    public Long fuelYouCanMakeWithATrillionOre() throws IOException {
        return fuelYouCanMakeWithNOre(ONE_TRILLION);

    }

    private long fuelYouCanMakeWithNOre(long availableOre) throws IOException {
        long lowerBound = 1;
        while (leastRequiredOreForNFuel(lowerBound) < availableOre) {
            lowerBound *= 2;
        }

        long upperBound = lowerBound;
        lowerBound /= 2;
        long midPoint = (upperBound + lowerBound) / 2;
        long dist = lowerBound;

        while (dist > 1) {

            long oreNeeded = leastRequiredOreForNFuel(midPoint);
            System.out.println(midPoint + " " + oreNeeded);
            if (oreNeeded < availableOre) {
                lowerBound = midPoint;
            } else if (oreNeeded > availableOre) {
                upperBound = midPoint;
            } else {
                return midPoint;
            }
            dist = upperBound - lowerBound;
            midPoint = (upperBound + lowerBound) / 2;
        }
        return lowerBound;
    }

    private long leastRequiredOreForNFuel(long N) throws IOException {

        class StoichDoer {
            final Map<String, Long> currentState = new HashMap<>();

            StoichDoer(long desiredFuel) throws IOException {
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

            private void applyReaction(String chemical) {
                Long currentQuantityOfChemical = currentState.getOrDefault(chemical, 0L);
                Integer reactionQuantityOfChemical = reactionInfo.getQuantityOfChemicalMadeByOneReaction(chemical);
                Long timesRun = Math.floorDiv(currentQuantityOfChemical, reactionQuantityOfChemical);

                currentState.put(chemical, currentQuantityOfChemical + (timesRun * reactionQuantityOfChemical));
                Map<String, Integer> inputChemicals = reactionInfo.getInputsForChemical(chemical);
                for (String inputChemical : inputChemicals.keySet()) {
                    currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0L) - (timesRun * inputChemicals.get(inputChemical)));
                }
            }

            private void applyInvReaction(String chemical) {
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
                            applyReaction(chemical);
                        }
                        return true;
                    }
                    if (currentState.get(chemical) > 0) {
                        while (currentState.get(chemical) > 0) {
                            applyInvReaction(chemical);
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


