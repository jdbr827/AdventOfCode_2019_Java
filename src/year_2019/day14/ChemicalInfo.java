package year_2019.day14;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChemicalInfo {
    private final ReactionInfo reactionInfo;
    final ReactionInfoReader reactionInformation;
    Map<String, Long> currentState = new HashMap<String, Long>();

    public ChemicalInfo(ReactionInfo reactionInfo, String fileName) throws IOException {
        this.reactionInfo = reactionInfo;
        this.reactionInformation = new ReactionInfoReader(fileName);

    }

    void balance() {
        while (isNotBalanced()) {
        }
    }

    public void applyReaction(String chemical) {
        Long currentQuantityOfChemical = currentState.getOrDefault(chemical, 0L);
        Integer reactionQuantityOfChemical = reactionInformation.getQuantityMade().get(chemical);
        Long timesRun = Math.floorDiv(currentQuantityOfChemical, reactionQuantityOfChemical);
        currentState.put(chemical, currentQuantityOfChemical + (timesRun * reactionQuantityOfChemical));
        Map<String, Integer> inputChemicals = reactionInformation.getReactionInputs().get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0L) - (timesRun * inputChemicals.get(inputChemical)));
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

    public boolean isNotBalanced() {
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