package year_2019.day14;

import lombok.AccessLevel;
import lombok.Getter;
import utils.ReadIn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReactionInfo implements IReactionInfo {
    static final Pattern reactionInfoPattern = Pattern.compile("([0-9]+) ([A-Z]+)");
    @Getter(AccessLevel.PUBLIC) private final Map<String, Integer> quantityMade = new HashMap<>();
    @Getter(AccessLevel.PUBLIC) private final Map<String, Map<String, Integer>> reactionInputs = new HashMap<>();


    public ReactionInfo(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            readInReaction(line);
        }
    }

    void readInReaction(String line) {
        String[] reactionInfo = line.split("=>");
        Map<String, Integer> inputVals = new HashMap<String, Integer>();
        for (String inputInfo : reactionInfo[0].split(",")) {
            Matcher m = reactionInfoPattern.matcher(inputInfo);
            ReadIn.findOrElseThrow(m, "Could not read in reaction info");
            Integer inputQuantity = Integer.parseInt(m.group(1));
            String inputChemical = m.group(2);
            inputVals.put(inputChemical, inputQuantity);
        }
        Matcher m = reactionInfoPattern.matcher(reactionInfo[1]);
        ReadIn.findOrElseThrow(m, "Could not read in reaction info");
        Integer outputQuantity = Integer.parseInt(m.group(1));
        String outputChemical = m.group(2);

        reactionInputs.put(outputChemical, inputVals);
        quantityMade.put(outputChemical, outputQuantity);
    }

    private Integer getQuantityOfChemicalMadeByOneReaction(String chemical) {
        return getQuantityMade().get(chemical);
    }

    private Map<String, Integer> getInputsForChemical(String chemical) {
        return getReactionInputs().get(chemical);
    }

    @Override
    public Reaction getReactionForChemical(String chemical) {
        return new Reaction(chemical, getInputsForChemical(chemical), getQuantityOfChemicalMadeByOneReaction(chemical));
    }

}