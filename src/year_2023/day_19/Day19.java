package year_2023.day_19;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import utils.AOCScanner;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
    Map<String, Workflow> workflowMap = new HashMap<>();

    private static final Pattern rulePattern = Pattern.compile("([xmsa])([><])([\\d]+):([\\w]+)");

    public Day19(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);

        String line;
        while (!Objects.equals(line = scanner.nextLine(), "")) {
            String[] splitLine = line.split("\\{");
            String workflowName = splitLine[0];
            String[] splitRules = splitLine[1].split(",");
            List<Rule> rules = new ArrayList<>();
            for (int i=0; i<splitRules.length - 1; i++) {
                Matcher m = rulePattern.matcher(line);
                if (m.find()) {
                    String fieldName = m.group(1);
                    boolean greaterThan = m.group(2).equals(">");
                    int testNum = Integer.parseInt(m.group(3));
                    String action = m.group(4);
                    Rule rule = new Rule(fieldName, greaterThan, testNum, action);
                    rules.add(rule);
                }
            }
            String defaultAction = splitRules[splitRules.length - 1];

            Workflow workflow = new Workflow(rules, defaultAction);

            workflowMap.put(workflowName, workflow);
        }

    }

    public int sumRatingNumbersOfAcceptedMachineParts() {
        return 0;
    }


    @AllArgsConstructor
    class MachinePart {
        int x;
        int m;
        int s;
        int a;

        int getField(String c) {
            switch (c) {
                case "x":
                    return x;
                case "m":
                    return m;
                case "s":
                    return s;
                case "a":
                    return a;
            }
            return 0; // Should never get here
        }

        boolean check() {
            String workflow = "in";
            while (!(workflow.equals("R") || workflow.equals("A"))) {
                workflow = workflowMap.get(workflow).applyTo(this);
            }

            return workflow.equals("A");
        }
    }

    @AllArgsConstructor
    class Workflow {
        List<Rule> rules;
        String defaultAction;

        String applyTo(MachinePart machinePart) {
            String action;
            for (Rule rule : rules) {
                if ((action = rule.applyTo(machinePart)) != null) {
                    return action;
                }
            }
            return defaultAction;
        }
    }


    @AllArgsConstructor
    class Rule {
        String fieldName;
        boolean greaterThan;
        int testNum;
        String action;


        String applyTo(MachinePart machinePart) {
            if (greaterThan) {
                if (machinePart.getField(fieldName) > testNum) {
                    return action;
                }
            }
            return null;
        }
    }
}
