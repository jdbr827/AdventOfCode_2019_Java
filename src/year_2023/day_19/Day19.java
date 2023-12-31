package year_2023.day_19;

import lombok.AllArgsConstructor;
import utils.AOCScanner;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
    Map<String, Workflow> workflowMap = new HashMap<>();
    Collection<MachinePart> machineParts;

    private static final Pattern rulePattern = Pattern.compile("([xmsa])([><])([\\d]+):([\\w]+)");
    private static final Pattern machinePattern = Pattern.compile("\\{x=([\\d]+),m=([\\d]+),a=([\\d]+),s=([\\d]+)}");

    public Day19(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);

        String line;
        while (!Objects.equals(line = scanner.nextLine(), "")) {
            String[] splitLine = line.split("\\{");
            String workflowName = splitLine[0];
            String[] splitRules = splitLine[1].split(",");
            List<Rule> rules = new ArrayList<>();
            for (int i=0; i<splitRules.length - 1; i++) {
                Matcher m = rulePattern.matcher(splitRules[i]);
                if (m.find()) {
                    String fieldName = m.group(1);
                    boolean greaterThan = m.group(2).equals(">");
                    int testNum = Integer.parseInt(m.group(3));
                    String action = m.group(4);
                    Rule rule = new Rule(fieldName, greaterThan, testNum, action);
                    rules.add(rule);
                }
            }
            String defaultAction = splitRules[splitRules.length - 1].substring(0, splitRules[splitRules.length-1].length() - 1);

            Workflow workflow = new Workflow(rules, defaultAction);

            workflowMap.put(workflowName, workflow);
        }

        machineParts = new ArrayList<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            Matcher m = machinePattern.matcher(line);
                if (m.find()) {
                    machineParts.add(new MachinePart(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))));
                }

        }

    }

    public int sumRatingNumbersOfAcceptedMachineParts() {
        return machineParts.stream()
                .filter(MachinePart::check)
                .map(MachinePart::getRatingNumber)
                .reduce(0, Math::addExact);
    }


    @AllArgsConstructor
    class MachinePart {
        int x;
        int m;
        int a;
        int s;

        int getField(String c) {
            switch (c) {
                case "x":
                    return x;
                case "m":
                    return m;
                case "a":
                    return a;
                case "s":
                    return s;
            }
            return 0; // Should never get here
        }

        boolean check() {
            String workflow = "in";
            while (!(workflow.equals("R") || workflow.equals("A"))) {
                workflow = workflowMap.get(workflow).apply(this);
            }
            return workflow.equals("A");
        }

        public int getRatingNumber() {
            return x + m + a + s;
        }
    }

    @AllArgsConstructor
    class Workflow implements Function<MachinePart, String> {
        List<Rule> rules;
        String defaultAction;

        public String apply(MachinePart machinePart) {
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

        Predicate<MachinePart> condition() {
            return greaterThan
                    ? machinePart -> machinePart.getField(fieldName) > testNum
                    : machinePart -> machinePart.getField(fieldName) < testNum;

        }


        /**
         * If the condition applies to the machine part, returns the next workflow to send to, otherwise null.
         * @param machinePart the machine part under test
         * @return the next workflow to send the machine part to, if applicable, otherwise null.
         */
        String applyTo(MachinePart machinePart) {
            return condition().test(machinePart)
                    ? action
                    : null;
        }
    }
}
