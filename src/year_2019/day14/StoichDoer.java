package year_2019.day14;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StoichDoer {

    final ReactionInfoReader reactionInformation;
    Map<String, Long> currentState = new HashMap<>();

    public StoichDoer(ReactionInfoReader reactionInformation, long desiredFuel) throws IOException {
        this.reactionInformation = reactionInformation;
        currentState.put("FUEL", desiredFuel);
        balance();
    }

    public static long findLeastRequiredOreForNFuel(ReactionInfoReader reactionInformation, long desiredFuel) throws IOException {
        StoichDoer stoichDoer =  new StoichDoer(reactionInformation, desiredFuel);
        return stoichDoer.currentState.getOrDefault("ORE", 0L);
    }

    public void balance() {
        while (isNotBalanced()) {
        }
    }

    private void applyReaction(String chemical) {
        Long currentQuantityOfChemical = currentState.getOrDefault(chemical, 0L);
        Integer reactionQuantityOfChemical = reactionInformation.getQuantityMade().get(chemical);
        Long timesRun = Math.floorDiv(currentQuantityOfChemical, reactionQuantityOfChemical);
        currentState.put(chemical, currentQuantityOfChemical + (timesRun * reactionQuantityOfChemical));
        Map<String, Integer> inputChemicals = reactionInformation.getReactionInputs().get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0L) - (timesRun * inputChemicals.get(inputChemical)));
        }
    }

    private void applyInvReaction(String chemical) {
        Long currentQuantityOfChemical = currentState.getOrDefault(chemical, 0L);
        Integer reactionQuantityOfChemical = reactionInformation.getQuantityMade().get(chemical);
        Long timesRun = (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);
        currentState.put(chemical, currentQuantityOfChemical - (timesRun * reactionQuantityOfChemical));
        Map<String, Integer> inputChemicals = reactionInformation.getReactionInputs().get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0L) + (timesRun * inputChemicals.get(inputChemical)));
        }
    }

    private boolean isNotBalanced() {
        for (String chemical : currentState.keySet()) {
            if (chemical.equals("ORE")) {
                continue;
            }
            if (-1L * reactionInformation.getQuantityMade().get(chemical) > currentState.get(chemical)) {
                while(-1L * reactionInformation.getQuantityMade().get(chemical) > currentState.get(chemical)) {
                    applyReaction(chemical);
                }
                return true;
            }
            if (currentState.get(chemical) > 0) {
                while(currentState.get(chemical) > 0) {
                    applyInvReaction(chemical);
                }
                return true;
            }
        }
        return false;


    }
}