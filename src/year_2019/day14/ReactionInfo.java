package year_2019.day14;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReactionInfo {
    final ReactionInformation reactionInformation;
    Map<String, Integer> currentState = new HashMap<>();

    ReactionInfo(String fileName) throws IOException {
        reactionInformation = new ReactionInformation(fileName);
    }


    public Integer findLeastRequiredOreForOneFuel() {
        currentState.put("FUEL", 1);
        while (!isBalanced()) {
        }
        return currentState.get("ORE");


    }

    public void applyReaction(String chemical) {
        currentState.put(chemical, currentState.getOrDefault(chemical, 0) + reactionInformation.getQuantityMade().get(chemical));
        Map<String, Integer> inputChemicals = reactionInformation.getReactionInputs().get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0) - inputChemicals.get(inputChemical));
        }
    }

    public void applyInvReaction(String chemical) {
        currentState.put(chemical, currentState.getOrDefault(chemical, 0) - reactionInformation.getQuantityMade().get(chemical));
        Map<String, Integer> inputChemicals = reactionInformation.getReactionInputs().get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0) + inputChemicals.get(inputChemical));
        }
    }

    public boolean isBalanced() {
        for (String chemical : currentState.keySet()) {
            if (chemical.equals("ORE")) {
                continue;
            }
            if (-1 * reactionInformation.getQuantityMade().get(chemical) > currentState.get(chemical)) {
                applyReaction(chemical);
                return false;
            }
            if (currentState.get(chemical) > 0) {
                applyInvReaction(chemical);
                return false;
            }
        }
        return true;


    }
}


class ChemicalState {

}

class Reaction {
    Map<String, Integer> inputs;
    Integer quantOutput;
}
