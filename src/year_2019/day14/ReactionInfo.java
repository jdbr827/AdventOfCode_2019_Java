package year_2019.day14;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReactionInfo {
    final ReactionInformation reactionInformation;
    Map<String, Long> currentState = new HashMap<>();
    final static long ONE_TRILLION = 1000000000000L;

    ReactionInfo(String fileName) throws IOException {
        reactionInformation = new ReactionInformation(fileName);
    }


    public Long findLeastRequiredOreForOneFuel() {
        return findLeastRequiredOreForNFuel(1L);
    }

    private void balance() {
        while (!isBalanced()) {}
    }

    public Long findFuelYouCanMakeWithATrillionOre() {
        long lowerBound = 1;
        currentState.put("FUEL", 1L);
        balance();
        while (findLeastRequiredOreForNFuel(lowerBound) < ONE_TRILLION) {
            lowerBound *= 2;
        }

        long upperBound = lowerBound;
        lowerBound /= 2;
        long midPoint = (upperBound + lowerBound) / 2;
        long dist = lowerBound;

        while (dist > 1) {

            long oreNeeded = findLeastRequiredOreForNFuel(midPoint);
            System.out.println(midPoint + " " + oreNeeded);
            if (oreNeeded < ONE_TRILLION) {
                lowerBound = midPoint;
            } else if (oreNeeded > ONE_TRILLION) {
                upperBound = midPoint;
            } else {
                return midPoint;
            }
            dist = upperBound - lowerBound;
            midPoint = (upperBound + lowerBound) / 2;
            balance();
            System.out.println(lowerBound + "  " + upperBound);
        }
        return lowerBound;

    }

    private long findLeastRequiredOreForNFuel(long N) {
        currentState.clear();
        currentState.put("FUEL", N);
        while (!isBalanced()) {
        }
        return currentState.get("ORE");
    }

    public void applyReaction(String chemical) {

        Long currentQuantityOfChemical = currentState.getOrDefault(chemical, 0L);
        Integer reactionQuantityOfChemical = reactionInformation.getQuantityMade().get(chemical);
        Long timesrun = Math.floorDiv(currentQuantityOfChemical, reactionQuantityOfChemical);
        currentState.put(chemical, currentQuantityOfChemical + (timesrun * reactionQuantityOfChemical));
        Map<String, Integer> inputChemicals = reactionInformation.getReactionInputs().get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0L) - (timesrun * inputChemicals.get(inputChemical)));
        }
    }

    public void applyInvReaction(String chemical) {
        Long currentQuantityOfChemical = currentState.getOrDefault(chemical, 0L);
        Integer reactionQuantityOfChemical = reactionInformation.getQuantityMade().get(chemical);
        Long timesRun = (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);
        currentState.put(chemical, currentQuantityOfChemical - (timesRun * reactionQuantityOfChemical));
        Map<String, Integer> inputChemicals = reactionInformation.getReactionInputs().get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0L) + (timesRun * inputChemicals.get(inputChemical)));
        }
    }

    public boolean isBalanced() {
        for (String chemical : currentState.keySet()) {
            if (chemical.equals("ORE")) {
                continue;
            }
            if (-1L * reactionInformation.getQuantityMade().get(chemical) > currentState.get(chemical)) {
                while(-1L * reactionInformation.getQuantityMade().get(chemical) > currentState.get(chemical)) {
                    applyReaction(chemical);
                }
                return false;
            }
            if (currentState.get(chemical) > 0) {
                while(currentState.get(chemical) > 0) {
                    applyInvReaction(chemical);
                }
                return false;
            }
        }
        return true;


    }
}
