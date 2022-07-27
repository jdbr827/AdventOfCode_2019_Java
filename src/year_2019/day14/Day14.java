package year_2019.day14;

import year_2019.day01.Day1Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.ReadIn.findOrElseThrow;

public class Day14 {
    Map<String, Integer> quantityMade = new HashMap<>();
    Map<String, Map<String, Integer>> reactionInputs = new HashMap<>();
    Map<String, Integer> currentState = new HashMap<>();

    private static final Pattern reactionInfoPattern = Pattern.compile("([0-9]+) ([A-Z]+)");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/year_2019/day14/day_14_small_input.txt"));
        String line;
        Day14 day14 = new Day14();
        while ((line = br.readLine()) != null) {
            day14.readInReaction(line);
        }
        day14.currentState.put("FUEL", 1);
        while (!day14.isBalanced()) {
            //System.out.println(day14.currentState);
        };
        System.out.println(day14.currentState.get("ORE"));
    }

    public void readInReaction(String line) {
        String[] reactionInfo = line.split("=>");
        Map<String, Integer> inputVals = new HashMap<>();
        for (String inputInfo : reactionInfo[0].split(",")) {
            Matcher m = reactionInfoPattern.matcher(inputInfo);
            findOrElseThrow(m, "Could not read in reaction info");
            Integer inputQuantity = Integer.parseInt(m.group(1));
            String inputChemical = m.group(2);
            inputVals.put(inputChemical, inputQuantity);
        }
        Matcher m = reactionInfoPattern.matcher(reactionInfo[1]);
        findOrElseThrow(m, "Could not read in reaction info");
        Integer outputQuantity = Integer.parseInt(m.group(1));
        String outputChemical = m.group(2);
        reactionInputs.put(outputChemical, inputVals);
        quantityMade.put(outputChemical, outputQuantity);
        //System.out.println(reactionInputs.entrySet());
    }

    public void applyReaction(String chemical) {
        currentState.put(chemical, currentState.getOrDefault(chemical, 0) + quantityMade.get(chemical));
        Map<String, Integer> inputChemicals = reactionInputs.get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0) - inputChemicals.get(inputChemical));
        }
    }

    public void applyInvReaction(String chemical) {
        currentState.put(chemical, currentState.getOrDefault(chemical, 0) - quantityMade.get(chemical));
        Map<String, Integer> inputChemicals = reactionInputs.get(chemical);
        for (String inputChemical : inputChemicals.keySet()) {
            currentState.put(inputChemical, currentState.getOrDefault(inputChemical, 0) + inputChemicals.get(inputChemical));
        }
    }

    public boolean isBalanced() {
        for (String chemical : currentState.keySet()) {
            if (chemical.equals("ORE")) {
                continue;
            }
            if (-1 * quantityMade.get(chemical) > currentState.get(chemical)) {
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
